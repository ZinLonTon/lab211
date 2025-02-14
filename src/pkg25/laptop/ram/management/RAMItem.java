package pkg25.laptop.ram.management;

import java.io.Serializable;

import java.time.YearMonth;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class RAMItem implements Serializable {
    private static final long serialVersionUID = 1L; // Thêm serialVersionUID cố định
    
    private String code; // Mã duy nhất cho RAM item (dạng RAMx_y)
    private String type; // Loại RAM (LPDDR5, DDR5, ...)
    private String bus;  // Tốc độ bus (ví dụ: 5600MHz, 4800MHz)
    private String brand; // Thương hiệu
    private int quantity; // Số lượng
    private String productionMonthYear; // Ngày sản xuất (MM/YYYY)
    private boolean active; // Trạng thái hoạt động (mặc định true)
    
    private double price; // Thuộc tính được thêm sau
    
    public RAMItem() {
    }

    public RAMItem(String code, String type, String bus, String brand, int quantity, String productionMonthYear, boolean active) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.productionMonthYear = productionMonthYear;
        this.active = active;
    }


    // Getter và setter cho từng thuộc tính
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductionMonthYear() {
        return productionMonthYear;
    }

    public void setProductionMonthYear(String productionMonthYear) {
        this.productionMonthYear = productionMonthYear;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
    
    
    
    
    
    // Phương thức getPrice() trả về giá theo loại RAM
    public double getPrice() {
        // Giả định giá cho từng loại RAM
        switch (type.toUpperCase()) {
            case "DDR4":
                return 50.0;
            case "LPDDR4":
                return 55.0;
            case "DDR5":
                return 70.0;
            case "LPDDR5":
                return 75.0;
            case "DDR3":
                return 40.0;
            case "LPDDR3":
                return 45.0;
            case "DDR2":
                return 30.0;
            case "LPDDR2":
                return 35.0;
            case "DDR1":
                return 20.0;
            case "LPDDR1":
                return 25.0;
            default:
                return 0.0; // Nếu loại không xác định
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Phương thức tính số ngày tồn kho từ productionMonthYear đến hôm nay
    public long getDaysInInventory() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        YearMonth productionDate = YearMonth.parse(productionMonthYear, formatter);
        LocalDate productionStartDate = productionDate.atDay(1); // Bắt đầu từ ngày đầu tiên của tháng

        return ChronoUnit.DAYS.between(productionStartDate, LocalDate.now());
    }
    
    
    
    
    
    
    @Override
    public String toString() {
        return "RAMItem{" + "code=" + code + ", type=" + type + ", bus=" + bus + ", brand=" + brand + ", quantity=" + quantity + ", productionMonthYear=" + productionMonthYear + ", active=" + active + '}';
    }

}




