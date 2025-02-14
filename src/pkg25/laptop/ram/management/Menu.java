package pkg25.laptop.ram.management;

public class Menu {
    
    public static void displayMenu() {
        //the \m mot dong
        // Logic for displaying menu options to the user
        System.out.println("=================================== MENU ===================================");
        System.out.println("1. Create an Item");
        System.out.println("2. Add an Item");
        System.out.println("3. Search SubMenu");
        System.out.println("4. Update Item Information");
        System.out.println("5. Delete Item");
        System.out.println("6. Show All Items");
        System.out.println("7. Store Data to Files");
        System.out.println("8. Quit Menu");
        System.out.println("9. Tính Tổng Giá Trị Tồn Kho Theo Thương Hiệu");
        System.out.println("10. Phân Tích số ngày Tồn Kho");
        System.out.println("11. Xác Định và Liệt Kê Các Mục Tồn Kho Thấp");
        System.out.println("12. Tính Số Lượng RAM Theo Năm Sản Xuất");
        System.out.println("13. Xác Định Thương Hiệu RAM Phổ Biến Nhất");
        System.out.println("14. Tính Tỉ Lệ Sử Dụng RAM");
        System.out.println("============================================================================");
    }
    
    
    public static void displaySubMenu() {
        System.out.println("=============== Sub Menu ===============");
        System.out.println("1. Tìm kiếm theo loại");
        System.out.println("2. Tìm kiếm theo bus");
        System.out.println("3. Tìm kiếm theo thương hiệu");
        System.out.println("4. Quay lại menu chính");
        System.out.println("5. Tổng số lượng RAM đang hoạt động");
        System.out.println("6. RAM có số lượng nhỏ nhất và lớn ");
        System.out.println("7. Tính số lượng trung bình của RAM");
        System.out.println("8. RAM có ngày sản xuất cũ nhất và mới nhất");
        System.out.println("9. Báo cáo tổng hợp");
        System.out.println("==============================================");
        System.out.print("Chọn loại tìm kiếm: ");
    }
    
    
    
    
}
