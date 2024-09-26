import java.util.ArrayList;
import java.util.List;

interface Subscriber {
    void notifyPriceChange(int price);
}

class StockMarket {
    private List<Subscriber> subscribers = new ArrayList<>();
    private int currentStockPrice;

    public void registerSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unregisterSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void updateStockPrice(int price) {
        this.currentStockPrice = price;
        informSubscribers();
    }

    private void informSubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.notifyPriceChange(currentStockPrice);
        }
    }
}

class Investor implements Subscriber {
    private String investorName;

    public Investor(String name) {
        this.investorName = name;
    }

    @Override
    public void notifyPriceChange(int price) {
        System.out.println(investorName + " received an update: Stock price is now " + price);
    }
}

// Demonstration
public class Main {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();
        Investor investor1 = new Investor("Investor 1");
        Investor investor2 = new Investor("Investor 2");

        market.registerSubscriber(investor1);
        market.registerSubscriber(investor2);

        market.updateStockPrice(100);
        market.updateStockPrice(110);
    }
}
