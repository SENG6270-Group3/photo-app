import java.util.List;

/**
 * Created by Michael on 2/24/2016.
 */
public class MultipleOptionsCalculator {

    /*3.2.3.2	When the user inputs a quantity of 4 x 6 size the application will add $ 0.19 for each print.
    3.2.3.2.1	When the user inputs a quantity of 5 x 7 size the application will add $ 0.39 for each print.
    3.2.3.2.2	When the user inputs a quantity of 8 x 10 size the application will add $ 0.79 for each print.
    */
    private double price4by6 = 0.19;
    private double price5by7 = 0.39;
    private double price8by10 = 0.79;

    /*3.2.3.3.1	When the user inputs a quantity of the 4 x 6 size to have a matte finish
    the application will add $ 0.04 for each print.
    3.2.3.3.2	When the user inputs a quantity of the 5 x 7 size
    the application will add $ 0.06 for each print.
    3.2.3.3.3	When the user inputs a quantity of the 8 x 10 size
    the application will add $ 0.08 for each print.
    */
    private double priceMatte4by6 = 0.04;
    private double priceMatte5by7 = 0.06;
    private double priceMatte8by10 = 0.08;

    /* 3.2.3.4	When the user inputs any prints for a processing time of 1-hour,
    the application will apply an additional amount based on the quantity of prints ordered for 1-hour processing.
    3.2.3.4.1	When the total order has at least one and less than or equal 60 prints,
    the application will add $ 2.00 to the total cost.
    3.2.3.4.2	When the total order has more than 60 prints,
    the application will add $ 2.50 to the total order
    */
    private double priceOneHour60AndUnder = 2.0;
    private double priceOneHourOver60 = 2.5;

    public double calculateMode2(List<PhotoInputSection> inputSections, String processingTime) {
        double total = 0.00;

        for (PhotoInputSection section : inputSections) {
            total += getPrice(section.getCount(), section.getImageSize(), section.getFinish());
        }

        if (processingTime.equals("1-HOUR")) {
            int quantity = getQuantityTotal(inputSections);
            total += calculateOneHour(quantity);
        }

        if (total > 35) {
            total = total * 0.95;
        }

        return total;
    }

    private double getPrice(int qty, String size, PhotoInputSection.Finish finish) {
        if (finish.equals(PhotoInputSection.Finish.MATTE)) {
            if (size.equals("4x6")) {
                return qty * (price4by6 + priceMatte4by6);
            } else if (size.equals("5x7")) {
                return qty * (price5by7 + priceMatte5by7);
            } else if (size.equals("8x10")) {
                return qty * (price8by10 + priceMatte8by10);
            }
        } else {
            if (size.equals("4x6")) {
                return qty * price4by6;
            } else if (size.equals("5x7")) {
                return qty * price5by7;
            } else if (size.equals("8x10")) {
                return qty * price8by10;
            }
        }

        //this should never happen
        return 0.0;
    }

    private double calculateOneHour(int quantityOneHour){
        if (quantityOneHour >= 1 && quantityOneHour <= 60){
            return priceOneHour60AndUnder;
        } else if (quantityOneHour > 60 && quantityOneHour <= 100) {
            return priceOneHourOver60;
        } else {
            return 0;
        }


    }

    private int getQuantityTotal(List<PhotoInputSection> inputs) {
        int total = 0;

        for (PhotoInputSection section : inputs) {
            total += section.getCount();
        }

        return total;
    }

}
