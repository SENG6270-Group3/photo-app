import java.util.List;

public class SingleOptionCalculator {

    private double PRICE_FIRST_50_4x6 = 0.14;
    private double PRICE_51_75_4x6 = 0.12;
    private double PRICE_76_100_4x6 = 0.10;

    private double PRICE_FIRST_50_5x7 = 0.34;
    private double PRICE_51_75_5x7 = 0.31;
    private double PRICE_76_100_5x7 = 0.28;

    private double PRICE_FIRST_50_8x10 = 0.68;
    private double PRICE_51_75_8x10 = 0.64;
    private double PRICE_76_100_8x10 = 0.60;

    private double MATTE_4x6 = 0.02;
    private double MATTE_5x7 = 0.03;
    private double MATTE_8x10 = 0.04;

    private double ONE_HOUR_0_60 = 1.00;
    private double ONE_HOUR_61_100 = 1.50;

    private String COUPON_CODE = "N56M2";
    private double COUPON_REDUCTION = 2.00;

    public double calculate(final List<PhotoInputSection> inputList, final String delivery, final String couponCode) {
        PhotoInputSection input = inputList.get(0);

        int quantity = inputList.size() > 1 ?
                getQuantityTotal(inputList) :
                input.getCount();


        String size = input.getImageSize();
        PhotoInputSection.Finish finish = input.getFinish();

        double price = 0.0;
        price += getSizePrice(quantity, size);
        price += getFinishPrice(quantity, finish, size);
        price += getProcessingTimePrice(quantity, delivery);
        if (isCouponValid(couponCode)) {
            price -= this.getPromotionDiscount(quantity, couponCode);
        }

        if (price > 35 && !isCouponValid(couponCode)) {
            price = price * 0.95;
        }

        return price;
    }

    public double getSizePrice(double qty, String size) {
        double total = 0.00;

        if (size.equals("4x6")) {
            if (qty >= 1 && qty <= 50) {
                total += qty * PRICE_FIRST_50_4x6;
            }
            else if (qty >= 51 && qty <= 75) {
                total += qty * PRICE_FIRST_50_4x6;
                total += (qty - 50) * PRICE_51_75_4x6;
            }
            else if (qty >= 76 && qty <= 100) {
                total += qty * PRICE_FIRST_50_4x6;
                total += (qty - 50) * PRICE_51_75_4x6;
                total += (qty - 75) * PRICE_76_100_4x6;
            }
        } else if (size.equals("5x7")) {
            if (qty >= 1 && qty <= 50) {
                total += qty * PRICE_FIRST_50_5x7;
            }
            else if (qty >= 51 && qty <= 75) {
                total += qty * PRICE_FIRST_50_5x7;
                total += (qty - 50) * PRICE_51_75_5x7;
            }
            else if (qty >= 76 && qty <= 100) {
                total += qty * PRICE_FIRST_50_5x7;
                total += (qty - 50) * PRICE_51_75_5x7;
                total += (qty - 75) * PRICE_76_100_5x7;
            }
        } else if (size.equals("8x10")) {
            if (qty >= 1 && qty <= 50) {
                total += qty * PRICE_FIRST_50_8x10;
            }
            else if (qty >= 51 && qty <= 75) {
                total += qty * PRICE_FIRST_50_8x10;
                total += (qty - 50) * PRICE_51_75_8x10;
            }
            else if (qty >= 76 && qty <= 100) {
                total += qty * PRICE_FIRST_50_8x10;
                total += (qty - 50) * PRICE_51_75_8x10;
                total += (qty - 75) * PRICE_76_100_8x10;
            }
        }

        return total;
    }

    private double getFinishPrice(double qty, PhotoInputSection.Finish finish, String size) {
        if (finish.equals(PhotoInputSection.Finish.MATTE)) {
            if (size.equals("4x6")) {
                return qty *  MATTE_4x6;
            } else if (size.equals("5x7")) {
                return qty * MATTE_5x7;
            } else if (size.equals("8x10")) {
                return qty * MATTE_8x10;
            }

        }

        return 0.0;
    }

    private double getProcessingTimePrice(double qty, String processingTime) {
        if (processingTime.equals("1-HOUR")) {
            if (qty <= 60) {
                return ONE_HOUR_0_60;
            } else {
                return ONE_HOUR_61_100;
            }
        }

        return 0.0;
    }


    private double getPromotionDiscount(double qty, String promoCode) {
        if (qty == 100 && promoCode.equals(COUPON_CODE)) {
            return COUPON_REDUCTION;
        } else {
            return 0.00;
        }
    }

    private boolean isCouponValid(String promoCode) {
        return promoCode.equals(COUPON_CODE);
    }

    private int getQuantityTotal(List<PhotoInputSection> inputs) {
        int total = 0;

        for (PhotoInputSection section : inputs) {
            total += section.getCount();
        }

        return total;
    }
}
