package pkg25.laptop.ram.management;

import java.util.*;
import java.io.*;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        RAMManagement ramSystem = new RAMManagement();
        RAMItem newItem = null; // Khởi tạo biến newItem ban đầu là null
        ramSystem.loadFromFile();

        // Vòng lặp chính để chạy menu
        boolean exit = false;
        while (!exit) {
            // Hiển thị menu
            Menu.displayMenu();

            int choice = -1; // Biến để lưu lựa chọn
            boolean validInput = false; // Cờ kiểm tra nhập liệu

            // Vòng lặp yêu cầu người dùng nhập lại nếu nhập sai
            while (!validInput) {
                try {
                    System.out.println("Enter your choice: ");
                    choice = sc.nextInt();
                    sc.nextLine(); // Đọc bỏ ký tự xuống dòng sau khi nhập số
                    validInput = true; // Đánh dấu rằng nhập liệu hợp lệ
                } catch (InputMismatchException e) {
                    System.out.println("Lỗi: Vui lòng nhập một số hợp lệ.");
                    sc.nextLine(); // Xóa bỏ phần nhập không hợp lệ khỏi bộ đệm
                }
            }

            // Xử lý lựa chọn từ người dùng
            switch (choice) {
                case 1:
                    // Xây dựng RAM item nhưng không thêm vào danh sách
                    newItem = ramSystem.buildRAMItem();
                    System.out.println("Now you can choose to add this item to the collection.");
                    break;
                case 2:
                    // Thêm RAM item vào danh sách nếu không trùng mã
                    if (newItem != null) {
                        ramSystem.addItemsToCollection(); // Thêm vào danh sách bên trong RAMManagement
                    } else {
                        System.out.println("No item created yet. Please build an item first.");
                    }
                    break;
                case 3:
                    ramSystem.searchSubMenu(); // Tìm kiếm RAM item
                    break;
                case 4:
                    ramSystem.updateRAMItem(); // Cập nhật RAM item
                    break;
                case 5:
                    ramSystem.deleteRAMItem(); // Xóa RAM item
                    break;
                case 6:
                    ramSystem.showAllItems(); // Hiển thị tất cả RAM items
                    break;
                case 7:
                    ramSystem.saveToFile(); // Lưu dữ liệu vào file
                    break;
                case 8:
                    System.out.println("Thoát chương trình...");
                    exit = true; // Đặt cờ thoát
                    ramSystem.saveToFile();
                    break;
                case 9:
                    ramSystem.calculateTotalInventoryValueByBrand();
                    break;   
                case 10:
                    ramSystem.calculateAverageDaysInInventory();
                    break; 
                case 11:
                    ramSystem.listLowInventoryItems();
                    break; 
                case 12:
                    ramSystem.calculateTotalQuantityByYear();
                    break; 
                case 13:
                    ramSystem.findMostPopularBrand();
                    break; 
                case 14:
                    ramSystem.calculateRAMUsagePercentageByType();
                    break; 
                default:
                    System.out.println("Exiting the program...");
                    exit = true;
                    ramSystem.saveToFile();
                    break;
            }
        }
    }
}
        
        
        
        
        
        
        
        

