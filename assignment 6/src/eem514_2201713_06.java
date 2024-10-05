import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.DecimalFormat;


public class eem514_2201713_06 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java _2b <input_file>");
            return;
        }

        String fileName = args[0];

        try {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            // Read investor's name and number of companies
            String investorName = scanner.nextLine();
            String[] firstLine = investorName.split(",");
            String name = firstLine[0].trim();
            int numCompanies = Integer.parseInt(firstLine[1].trim());

            // Creating an investor object
            Investor investor = new Investor(name, numCompanies);

            // Read stock details from the input file
            readInput(scanner, investor);

            // Display the report
            report(investor);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void readInput(Scanner scanner, Investor investor) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("END")) {
                break;
            }

            // Parse stock symbol, current price, and number of purchases
            String[] stockInfo = line.split(",");
            String symbol = stockInfo[0].trim();
            double currPrice = Double.parseDouble(stockInfo[1].trim());
            int numPurchases = Integer.parseInt(stockInfo[2].trim());

            // Create a new stock object
            Stock stock = new Stock(symbol, currPrice);

            // For each purchase, read number of shares and share price
            for (int i = 0; i < numPurchases; i++) {
                String[] purchaseInfo = scanner.nextLine().split(",");
                int numShares = Integer.parseInt(purchaseInfo[0].trim());
                double sharePrice = Double.parseDouble(purchaseInfo[1].trim());
                stock.purchase(numShares, sharePrice);
            }

            // Add the stock to the investor's stock list
            investor.addStock(stock);
        }
    }

    public static void report(Investor investor) {
        investor.displayStockSummary();
    }
}

class Stock {
    private String symbol;
    private int totalShares; // total shares purchased
    private double totalCost; // total cost for all shares
    private double currentPrice;

    public Stock(String theSymbol, double currentPrice) {
        this.symbol = theSymbol;
        this.currentPrice = currentPrice;
        this.totalShares = 0;
        this.totalCost = 0;
    }

    // Method to return the total profit or loss earned on this stock
    public double getProfit() {
        return (currentPrice * totalShares) - totalCost;
    }

    // Method to update the total cost after a purchase
    public void purchase(int shares, double pricePerShare) {
        totalShares += shares;
        totalCost += shares * pricePerShare;
    }

    // Obvious
    public String getSymbol() {
        return symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }
}

// An Investor class that represents an investor,
// who can make multiple stock purchases of several companies

class Investor {
    private String investorName; // Investor Name
    private Stock[] cosList; // Array of Stock objects
    // Each Stock represents one company.
    // Each Stock object computes its own overall profit.
    private int stockIndex;
    private static final DecimalFormat df = new DecimalFormat("#,###.00");

    public Investor(String name, int n) {
        this.investorName = name;
        this.cosList = new Stock[n];
        this.stockIndex = 0;
    }

    // Method to add a stock object to the array above
    public void addStock(Stock s) {
        cosList[stockIndex++] = s;
    }

    // Method(s) to display Stock Summary and best/worst scrips
    public void displayStockSummary() {
        System.out.println("Investor Name: " + investorName);
        System.out.println("Stock     Curr Price    Profit/Loss");

        double totalProfitLoss = 0.0;
        Stock mostProfitable = null;
        Stock leastProfitable = null;

        for (Stock stock : cosList) {
            double profit = stock.getProfit();
            totalProfitLoss += profit;

            System.out.printf("%-10s %10.2f %15s%n", stock.getSymbol(), stock.getCurrentPrice(), df.format(profit));

            if (mostProfitable == null || profit > mostProfitable.getProfit()) {
                mostProfitable = stock;
            }
            if (leastProfitable == null || profit < leastProfitable.getProfit()) {
                leastProfitable = stock;
            }
        }

        System.out.printf("Nett Profit/Loss as of today: ₹%s%n", df.format(totalProfitLoss));
        if (mostProfitable != null) {
            System.out.println(investorName + "'s most profitable scrip is: " + mostProfitable.getSymbol() +
                    " (Price: " + df.format(mostProfitable.getCurrentPrice()) + " Profit: " + df.format(mostProfitable.getProfit()) + ")");
        }
        if (leastProfitable != null) {
            System.out.println("What " + investorName + " should probably sell off: " + leastProfitable.getSymbol());
        }
    }
}
