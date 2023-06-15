package kodlama.io.ecommerce.common.constants;

public class Messages {
    public static class Product {
        public static final String NotExists = "PRODUCT_NOT_EXISTS";
        public static final String Exists = "PRODUCT_ALREADY_EXISTS";
        public static final String ProductPassive = "PRODUCT_IS_PASSIVE";
        public static final String NotEnoughQuantity="NOT_ENOUGH_QUANTITY";
    }

    public static class Category {
        public static final String NotExists = "CATEGORY_NOT_EXISTS";
        public static final String Exists = "CATEGORY_ALREADY_EXISTS";
    }

    public static class Sale {
        public static final String NotExists = "SALE_NOT_EXISTS";
        public static final String Exists = "SALE_ALREADY_EXISTS";
    }

    public static class Payment {
        public static final String NotFound = "PAYMENT_NOT_FOUND";
        public static final String CardNumberAlreadyExists = "CARD_NUMBER_ALREADY_EXISTS";
        public static final String NotEnoughMoney = "NOT_ENOUGH_MONEY";
        public static final String NotAValidPayment = "NOT_A_VALID_PAYMENT";
        public static final String Failed = "PAYMENT_FAILED";
    }
}
