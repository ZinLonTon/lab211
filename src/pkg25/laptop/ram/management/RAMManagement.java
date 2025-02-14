package pkg25.laptop.ram.management;

import pkg25.laptop.ram.management.RAMItem;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors; // Đảm bảo đã import Collectors

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.YearMonth;






public class RAMManagement {
    File file = new File("RAMModules.dat");


    public List<RAMItem> ramItems = new ArrayList<>();
    public List<RAMItem> temporaryItems = new ArrayList<>(); // Danh sách tạm thời để lưu các RAMItem được tạo
    public int globalCount = 0;                           // Sử dụng một bộ đếm toàn cầu cho tất cả các loại RAM
    
    // Lưu dữ liệu vào file nhị phân .dat
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(ramItems); // Lưu danh sách RAM items
            oos.writeInt(globalCount); // Lưu giá trị globalCount

            System.out.println("Data and globalCount have been successfully saved to RAMModules.dat");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu dữ liệu: " + e.getMessage());
        }
    }


    // Nạp dữ liệu từ file nhị phân .dat
    public void loadFromFile() {
        if (!file.exists()) {
            System.out.println("File không tồn tại. Có thể là lần đầu tiên chạy chương trình.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ramItems = (List<RAMItem>) ois.readObject(); // Nạp danh sách RAM items
            globalCount = ois.readInt(); // Nạp giá trị globalCount

//            System.out.println("Data and globalCount have been successfully loaded from RAMModules.dat");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }










    
    
    
    
    
    
    
    
    // chức năng 1
    public RAMItem buildRAMItem() {
        Scanner sc = new Scanner(System.in);

        // Nhập và kiểm tra type không được trống
        String type;
        do {
            System.out.print("Enter RAM type (e.g., LPDDR5, DDR5, LPDDR4, DDR4, DDR3, LPDDR3): ");
            type = sc.nextLine().trim().toUpperCase(); // Chuyển về chữ hoa
            if (type.isEmpty()) {
                System.out.println("RAM type cannot be empty. Please enter a valid type.");
            }
        } while (type.isEmpty());

        // Hiển thị ví dụ bus speed dựa trên loại RAM đã nhập
        switch (type) {
            case "DDR5":
                System.out.println("For DDR5, valid bus speeds include: 4800MHz, 5200MHz, 5600MHz.");
                break;
            case "LPDDR5":
                System.out.println("For LPDDR5, valid bus speeds include: 4266MHz, 5500MHz, 6400MHz.");
                break;
            case "DDR4":
                System.out.println("For DDR4, valid bus speeds include: 2400MHz, 2666MHz, 3200MHz.");
                break;
            case "LPDDR4":
                System.out.println("For LPDDR4, valid bus speeds include: 2133MHz, 3200MHz, 4266MHz.");
                break;
            case "DDR3":
                System.out.println("For DDR3, valid bus speeds include: 1333MHz, 1600MHz, 1866MHz.");
                break;
            case "LPDDR3":
                System.out.println("For LPDDR3, valid bus speeds include: 1600MHz, 1866MHz, 2133MHz.");
                break;
            case "DDR2":
                System.out.println("For DDR2, valid bus speeds include: 533MHz, 667MHz, 800MHz.");
                break;
            case "LPDDR2":
                System.out.println("For LPDDR2, valid bus speeds include: 800MHz, 1066MHz.");
                break;
            case "DDR1":
                System.out.println("For DDR1, valid bus speeds include: 266MHz, 333MHz, 400MHz.");
                break;
            case "LPDDR1":
                System.out.println("For LPDDR1, valid bus speeds include: 200MHz, 266MHz, 333MHz.");
                break;
            default:
                System.out.println("Unknown or unsupported RAM type. Please ensure the RAM type is correct.");
                break;
        }

        // Nhập và kiểm tra bus speed không được trống và không được âm
        String bus;
        boolean validBusForType = false;
        do {
            System.out.print("Enter bus speed: ");
            bus = sc.nextLine().trim();

            // Thêm "MHz" vào cuối nếu người dùng chỉ nhập số
            if (!bus.toLowerCase().endsWith("mhz")) {
                bus += "MHz";
            }

            try {
                // Chuyển đổi phần số của bus speed thành số nguyên
                String busNumber = bus.replace("MHz", "").trim();
                int busValue = Integer.parseInt(busNumber);

                // Kiểm tra điều kiện số bus dựa trên loại RAM
                switch (type.toUpperCase()) {
                    case "DDR5":
                        if (busValue == 4800 || busValue == 5200 || busValue == 5600) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for DDR5. Please enter 4800MHz, 5200MHz, or 5600MHz.");
                        }
                        break;
                    case "LPDDR5":
                        if (busValue == 4266 || busValue == 5500 || busValue == 6400) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for LPDDR5. Please enter 4266MHz, 5500MHz, or 6400MHz.");
                        }
                        break;
                    case "DDR4":
                        if (busValue == 2400 || busValue == 2666 || busValue == 3200) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for DDR4. Please enter 2400MHz, 2666MHz, or 3200MHz.");
                        }
                        break;
                    case "LPDDR4":
                        if (busValue == 2133 || busValue == 3200 || busValue == 4266) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for LPDDR4. Please enter 2133MHz, 3200MHz, or 4266MHz.");
                        }
                        break;
                    case "DDR3":
                        if (busValue == 1333 || busValue == 1600 || busValue == 1866) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for DDR3. Please enter 1333MHz, 1600MHz, or 1866MHz.");
                        }
                        break;
                    case "LPDDR3":
                        if (busValue == 1600 || busValue == 1866 || busValue == 2133) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for LPDDR3. Please enter 1600MHz, 1866MHz, or 2133MHz.");
                        }
                        break;
                    case "DDR2":
                        if (busValue == 533 || busValue == 667 || busValue == 800) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for DDR2. Please enter 533MHz, 667MHz, or 800MHz.");
                        }
                        break;
                    case "LPDDR2":
                        if (busValue == 800 || busValue == 1066) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for LPDDR2. Please enter 800MHz or 1066MHz.");
                        }
                        break;
                    case "DDR1":
                        if (busValue == 266 || busValue == 333 || busValue == 400) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for DDR1. Please enter 266MHz, 333MHz, or 400MHz.");
                        }
                        break;
                    case "LPDDR1":
                        if (busValue == 200 || busValue == 266 || busValue == 333) {
                            validBusForType = true;
                        } else {
                            System.out.println("Invalid bus speed for LPDDR1. Please enter 200MHz, 266MHz, or 333MHz.");
                        }
                        break;
                    default:
                        System.out.println("Unknown or unsupported RAM type. Please check the type and try again.");
                        break;
                }
                // Nếu không hợp lệ, đặt lại `bus` để lặp lại
                if (!validBusForType) {
                    bus = "";
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid bus speed format. Please enter a valid number followed by 'MHz'.");
                bus = "";
            }
        } while (bus.isEmpty() || !validBusForType);

        // Nhập và kiểm tra brand không được trống
        String brand;
        do {
            System.out.print("Enter brand: ");
            brand = sc.nextLine().trim();
            if (brand.isEmpty()) {
                System.out.println("Brand cannot be empty. Please enter a valid brand.");
            }
        } while (brand.isEmpty());

        // Nhập và kiểm tra quantity không được âm
        int quantity = -1;
        do {
            System.out.print("Enter quantity: ");
            try {
                quantity = sc.nextInt();
                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative. Please enter a valid quantity.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
            }
        } while (quantity < 0);
        sc.nextLine();

        // Nhập và kiểm tra productionMonthYear không được trống
        String productionMonthYear;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        do {
            System.out.print("Enter production month/year (MM/YYYY): ");
            productionMonthYear = sc.nextLine().trim();
            try {
                YearMonth.parse(productionMonthYear, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please enter the date in MM/YYYY format.");
                productionMonthYear = "";
            }
        } while (productionMonthYear.isEmpty());

        // Tạo mã mới cho RAM item
        globalCount++;
        String code = String.format("RAM%s_%d", type, globalCount);

        // Tạo đối tượng RAMItem
        RAMItem newItem = new RAMItem(code, type, bus, brand, quantity, productionMonthYear, true);

        System.out.println("RAM item created: " + newItem);

        // Thêm sản phẩm vào danh sách tạm thời
        temporaryItems.add(newItem);

        return newItem; // Trả về RAMItem đã được tạo
    }


    // Chức năng 2: Thêm tất cả RAMItem từ danh sách tạm thời vào bộ sưu tập
    public void addItemsToCollection() {
        if (!temporaryItems.isEmpty()) {
            for (RAMItem ramItem : temporaryItems) {
                // Kiểm tra xem mã sản phẩm đã tồn tại trong danh sách và đang hoạt động hay không
                boolean itemExists = ramItems.stream()
                        .anyMatch(item -> item.getCode().equals(ramItem.getCode()) && item.isActive());

                if (!itemExists) {
                    // Nếu mã sản phẩm không tồn tại hoặc đã bị đánh dấu là không hoạt động, thêm sản phẩm vào danh sách
                    ramItems.add(ramItem);
                    System.out.println("RAM item " + ramItem.getCode() + " added successfully.");
                } else {
                    System.out.println("RAM item with code " + ramItem.getCode() + " already exists and is active. Skipping addition.");
                }
            }
            // Xóa danh sách tạm sau khi đã thêm vào bộ sưu tập chính
            temporaryItems.clear();
            saveToFile(); // Tự động lưu file sau khi thêm thành công
        } else {
            System.out.println("No items to add to the collection.");
        }
    }








    
    
    
    
  







    
    
    
    
    
    
    
    
    // Sub Menu
    public void searchSubMenu() {
        Scanner sc = new Scanner(System.in);
        // Vòng lặp chính để chạy menu
        boolean exit = false;
        while (!exit) {
            // Hiển thị menu
            Menu.displaySubMenu();

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

            switch (choice) {
                case 1:
                    searchByType();
                    break;
                case 2:
                    searchByBus();
                    break;
                case 3:
                    searchByBrand();
                    break;
                case 5:
                    calculateTotalQuantity();
                    break;
                case 6:
                    calculateMinMaxQuantity();
                    break;
                case 7:
                    calculateAverageQuantity();
                    break;
                case 8:
                    showOldestAndNewestProductionDate();
                    break;
                case 9:
                    generateSummaryReport();
                    break;
                case 4:
                    System.out.println("Exiting the Sub Menu...");
                    exit = true;
                    break;  // Quay lại menu chính
                default:
                    System.out.println("Exiting the Sub Menu...");
                    exit = true;
                    break;  // Quay lại menu chính
            }
        }
    }


    // Tìm kiếm theo loại RAM
    private void searchByType() {
        Scanner sc = new Scanner(System.in);
        final String type; // Khai báo biến là "final"

        // Nhập loại RAM và kiểm tra đầu vào không được để trống
        while (true) {
            System.out.print("Nhập loại RAM để tìm kiếm: ");
            String inputType = sc.nextLine().trim();
            if (!inputType.isEmpty()) {
                type = inputType; // Gán giá trị một lần duy nhất cho biến final
                break;
            }
            System.out.println("Loại RAM không được để trống! Vui lòng nhập lại.");
        }

        // Tiếp tục với phần còn lại của phương thức
        System.out.println("Danh sách RAM phù hợp:");
        System.out.printf("%-12s | %-10s | %-21s | %-10s%n", "Code", "Type", "Production Month Year", "Quantity");

        List<RAMItem> filteredItems = ramItems.stream()
                .filter(item -> item.isActive() && item.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());

        if (filteredItems.isEmpty()) {
            System.out.println("Không có RAM nào phù hợp với loại đã chọn.");
        } else {
            filteredItems.forEach(item -> System.out.printf("%-12s | %-10s | %-21s | %-10d%n",
                    item.getCode(), item.getType(), item.getProductionMonthYear(), item.getQuantity()));
        }
    }

    // Tìm kiếm theo bus speed của RAM
    private void searchByBus() {
        Scanner sc = new Scanner(System.in);
        final String bus; // Khai báo là final

        // Nhập bus speed và kiểm tra đầu vào không được để trống
        while (true) {
            System.out.print("Nhập bus speed để tìm kiếm (e.g., 2400MHz): ");
            String inputBus = sc.nextLine().trim();
            if (!inputBus.isEmpty()) {
                bus = inputBus; // Gán giá trị một lần duy nhất cho biến final
                break;
            }
            System.out.println("Bus speed không được để trống! Vui lòng nhập lại.");
        }

        System.out.println("Danh sách RAM phù hợp:");
        System.out.printf("%-12s | %-10s | %-21s | %-10s%n", "Code", "Bus", "Production Month Year", "Quantity");

        // Lọc và hiển thị các item đang hoạt động với bus speed phù hợp
        List<RAMItem> filteredItems = ramItems.stream()
                .filter(item -> item.isActive() && item.getBus().equalsIgnoreCase(bus))
                .collect(Collectors.toList());

        if (filteredItems.isEmpty()) {
            System.out.println("Không có RAM nào phù hợp với bus speed đã chọn.");
        } else {
            filteredItems.forEach(item -> System.out.printf("%-12s | %-10s | %-21s | %-10d%n",
                    item.getCode(), item.getBus(), item.getProductionMonthYear(), item.getQuantity()));
        }
    }

    // Tìm kiếm theo thương hiệu của RAM
    private void searchByBrand() {
        Scanner sc = new Scanner(System.in);
        final String brand; // Khai báo là final

        // Nhập thương hiệu và kiểm tra đầu vào không được để trống
        while (true) {
            System.out.print("Nhập thương hiệu để tìm kiếm: ");
            String inputBrand = sc.nextLine().trim();
            if (!inputBrand.isEmpty()) {
                brand = inputBrand; // Gán giá trị một lần duy nhất cho biến final
                break;
            }
            System.out.println("Thương hiệu không được để trống! Vui lòng nhập lại.");
        }

        System.out.println("Danh sách RAM phù hợp:");
        System.out.printf("%-12s | %-15s | %-21s | %-10s%n", "Code", "Brand", "Production Month Year", "Quantity");

        // Lọc và hiển thị các item đang hoạt động với thương hiệu phù hợp
        List<RAMItem> filteredItems = ramItems.stream()
                .filter(item -> item.isActive() && item.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());

        if (filteredItems.isEmpty()) {
            System.out.println("Không có RAM nào phù hợp với thương hiệu đã chọn.");
        } else {
            filteredItems.forEach(item -> System.out.printf("%-12s | %-15s | %-21s | %-10d%n",
                    item.getCode(), item.getBrand(), item.getProductionMonthYear(), item.getQuantity()));
        }
    }






    
    
    
    
    
    
    
    
    
    
    // Cập nhật thông tin RAM item
    public void updateRAMItem() {
        Scanner sc = new Scanner(System.in);

        // Hiển thị tất cả các sản phẩm trước khi yêu cầu người dùng nhập mã RAM để cập nhật
        showAllItems();

        // Yêu cầu người dùng nhập mã RAM để cập nhật
        System.out.print("Nhập mã RAM để cập nhật: ");
        String code = sc.nextLine();

        // Tìm RAM item dựa trên mã
        RAMItem itemToUpdate = ramItems.stream()
                .filter(item -> item.getCode().equalsIgnoreCase(code))
                .findFirst().orElse(null);

        // Kiểm tra xem mã có tồn tại hay không
        if (itemToUpdate == null) {
            System.out.println("Mã RAM không tồn tại.");
            return; // Trở về menu chính nếu không tìm thấy RAM item
        }

        // Kiểm tra xem RAM item có đang hoạt động không
        if (!itemToUpdate.isActive()) {
            System.out.print("RAM item này đã bị xóa. Bạn có muốn khôi phục lại không? (Yes/No): ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                itemToUpdate.setActive(true); // Khôi phục RAM item
                System.out.println("RAM item đã được khôi phục thành công.");
                return;
            } else {
                System.out.println("RAM item không được khôi phục.");
                return; // Trở về menu nếu không muốn khôi phục
            }
        }

        // Hiển thị thông tin hiện tại
        System.out.printf("%-12s |%-10s |%-10s |%-15s |%-21s |%-10s%n",
                "Code", "Type", "Bus", "Brand", "Production Month Year", "Quantity");
        System.out.printf("%-12s ,%-10s ,%-10s ,%-15s ,%-21s ,%-3d%n",
                itemToUpdate.getCode(), itemToUpdate.getType(), itemToUpdate.getBus(),
                itemToUpdate.getBrand(), itemToUpdate.getProductionMonthYear(), itemToUpdate.getQuantity());

        System.out.println("Nhập thông tin mới (để trống nếu không thay đổi):");

        // Cập nhật bus speed (phải là số không âm và hợp lệ theo loại RAM)
        String currentType = itemToUpdate.getType(); // Lấy loại RAM hiện tại để kiểm tra bus speed hợp lệ
        String newBus;
        boolean validBusForType = false;
        do {
            System.out.print("Nhập bus speed mới (ví dụ: 3200MHz): ");
            newBus = sc.nextLine().trim();

            // Thêm "MHz" vào cuối nếu người dùng chỉ nhập số
            if (!newBus.isEmpty() && !newBus.toLowerCase().endsWith("mhz")) {
                newBus += "MHz";
            }

            if (!newBus.isEmpty()) {
                try {
                    String busNumber = newBus.replace("MHz", "").trim();
                    int busValue = Integer.parseInt(busNumber);

                    // Kiểm tra tính hợp lệ của bus dựa trên loại RAM
                    switch (currentType.toUpperCase()) {
                        case "DDR4":
                            if (busValue == 2400 || busValue == 2666 || busValue == 3200) {
                                validBusForType = true;
                            } else {
                                System.out.println("Invalid bus speed for DDR4. Please enter 2400MHz, 2666MHz, or 3200MHz.");
                            }
                            break;
                        case "DDR5":
                            if (busValue == 4800 || busValue == 5200 || busValue == 5600) {
                                validBusForType = true;
                            } else {
                                System.out.println("Invalid bus speed for DDR5. Please enter 4800MHz, 5200MHz, or 5600MHz.");
                            }
                            break;
                        case "LPDDR4":
                            if (busValue == 2133 || busValue == 3200 || busValue == 4266) {
                                validBusForType = true;
                            } else {
                                System.out.println("Invalid bus speed for LPDDR4. Please enter 2133MHz, 3200MHz, or 4266MHz.");
                            }
                            break;
                        case "LPDDR5":
                            if (busValue == 4266 || busValue == 5500 || busValue == 6400) {
                                validBusForType = true;
                            } else {
                                System.out.println("Invalid bus speed for LPDDR5. Please enter 4266MHz, 5500MHz, or 6400MHz.");
                            }
                            break;
                        default:
                            System.out.println("Unknown RAM type. Please check the type and try again.");
                            break;
                    }
                    if (validBusForType) {
                        itemToUpdate.setBus(newBus); // Cập nhật nếu bus hợp lệ
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Định dạng bus speed không hợp lệ, giữ nguyên giá trị cũ.");
                }
            }
        } while (!newBus.isEmpty() && !validBusForType);

        // Cập nhật thương hiệu
        System.out.print("Nhập thương hiệu mới: ");
        String newBrand = sc.nextLine();
        if (!newBrand.isEmpty()) {
            itemToUpdate.setBrand(newBrand);
        }

        // Cập nhật số lượng (phải là số dương)
        System.out.print("Nhập số lượng mới: ");
        String newQuantityStr = sc.nextLine();
        if (!newQuantityStr.isEmpty()) {
            try {
                int newQuantity = Integer.parseInt(newQuantityStr);
                if (newQuantity >= 0) {
                    itemToUpdate.setQuantity(newQuantity);
                } else {
                    System.out.println("Số lượng phải là số không âm, giữ nguyên số lượng hiện tại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Số lượng không hợp lệ, giữ nguyên số lượng hiện tại.");
            }
        }

        System.out.println("Cập nhật thông tin RAM thành công.");

        // Lưu lại sau khi cập nhật
        saveToFile();
    }











    
    
    
    // Xóa Item
    // Đánh dấu RAM item là không hoạt động
    public void deleteRAMItem() {
        Scanner sc = new Scanner(System.in);

        // Hiển thị tất cả các sản phẩm đang hoạt động trước khi yêu cầu người dùng nhập mã RAM để xóa
        showAllItems();

        System.out.print("Nhập mã RAM để đánh dấu là không hoạt động: ");
        String code = sc.nextLine();

        // Tìm RAM item dựa trên mã
        RAMItem itemToDeactivate = ramItems.stream()
                .filter(item -> item.getCode().equalsIgnoreCase(code) && item.isActive())
                .findFirst().orElse(null);

        // Kiểm tra xem mã có tồn tại hay không
        if (itemToDeactivate == null) {
            System.out.println("Mã RAM không tồn tại hoặc đã không hoạt động.");
            return;
        }

        // Hiển thị thông tin của RAM item cần đánh dấu là không hoạt động
        System.out.println("Thông tin RAM item sẽ đánh dấu là không hoạt động: ");
        System.out.printf("%-12s |%-10s |%-10s |%-15s |%-21s |%-10s%n",
                "Code", "Type", "Bus", "Brand", "Production Month Year", "Quantity");
        System.out.printf("%-12s ,%-10s ,%-10s ,%-15s ,%-21s ,%-10d%n",
                itemToDeactivate.getCode(),
                itemToDeactivate.getType(),
                itemToDeactivate.getBus(),
                itemToDeactivate.getBrand(),
                itemToDeactivate.getProductionMonthYear(),
                itemToDeactivate.getQuantity());

        // Hiển thị thông báo xác nhận
        System.out.print("Bạn có chắc chắn muốn đánh dấu mục này là không hoạt động? (Yes/No): ");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("ye") || choice.equalsIgnoreCase("y")) {
            // Đánh dấu RAM item là không hoạt động
            itemToDeactivate.setActive(false);
            System.out.println("Mục RAM đã được đánh dấu là không hoạt động.");

            // Lưu lại danh sách sau khi cập nhật trạng thái
            saveToFile();
        } else {
            System.out.println("Hủy bỏ thay đổi trạng thái của mục RAM.");
        }
    }







    
    
    
    
    
    
    
    
    
    
    // Hiển thị tất cả các RAM item đang hoạt động
    public void showAllItems() {
        System.out.println("Danh sách tất cả các RAM item đang hoạt động:");
        List<RAMItem> activeItems = ramItems.stream()
                .filter(RAMItem::isActive) // Chỉ lấy các item đang hoạt động
                .sorted(Comparator.comparing(RAMItem::getType)
                        .thenComparing(item -> {
                            String busNumber = item.getBus().replaceAll("\\D", "");
                            return Integer.parseInt(busNumber);
                        })
                        .thenComparing(RAMItem::getBrand))
                .collect(Collectors.toList());

        if (activeItems.isEmpty()) {
            System.out.println("Không có RAM item nào đang hoạt động.");
        } else {
            System.out.printf("%-12s | %-10s | %-10s | %-15s | %-21s | %-10s%n",
                    "Code", "Type", "Bus", "Brand", "Production Month Year", "Quantity");
            for (RAMItem item : activeItems) {
                System.out.printf("%-12s | %-10s | %-10s | %-15s | %-21s | %-3d%n",
                        item.getCode(), item.getType(), item.getBus(), item.getBrand(),
                        item.getProductionMonthYear(), item.getQuantity());
            }
        }
    }






    
    
    
    // Phương thức tính tổng số lượng RAM hiện có trong kho đối với các sản phẩm đang hoạt động
    public void calculateTotalQuantity() {
        // Tính tổng số lượng của các RAM đang hoạt động
        int totalQuantity = ramItems.stream()
                .filter(RAMItem::isActive) // Chỉ tính các item đang hoạt động
                .mapToInt(RAMItem::getQuantity) // Lấy số lượng của từng item
                .sum(); // Tính tổng số lượng

        // Hiển thị tổng số lượng cho người dùng
        System.out.println("Tổng số lượng RAM hiện có trong kho (đang hoạt động): " + totalQuantity);
    }

    // Phương thức tính số lượng nhỏ nhất và lớn nhất của RAM hiện có trong kho
    public void calculateMinMaxQuantity() {
        // Lọc danh sách các RAM đang hoạt động
        List<RAMItem> activeItems = ramItems.stream()
                .filter(RAMItem::isActive) // Chỉ lấy các item đang hoạt động
                .collect(Collectors.toList());

        // Kiểm tra xem có RAM nào đang hoạt động hay không
        if (activeItems.isEmpty()) {
            System.out.println("Không có RAM item nào đang hoạt động.");
            return; // Không thực hiện tính toán nếu không có RAM hoạt động
        }

        // Tìm giá trị nhỏ nhất và lớn nhất của quantity
        int minQuantity = activeItems.stream()
                .mapToInt(RAMItem::getQuantity) // Chuyển thành các số lượng của RAM
                .min() // Tìm giá trị nhỏ nhất
                .orElse(0); // Trả về 0 nếu không có giá trị hợp lệ

        int maxQuantity = activeItems.stream()
                .mapToInt(RAMItem::getQuantity) // Chuyển thành các số lượng của RAM
                .max() // Tìm giá trị lớn nhất
                .orElse(0); // Trả về 0 nếu không có giá trị hợp lệ

        // Hiển thị số lượng nhỏ nhất và lớn nhất cho người dùng
        System.out.println("Số lượng nhỏ nhất của RAM đang hoạt động: " + minQuantity);
        System.out.println("Số lượng lớn nhất của RAM đang hoạt động: " + maxQuantity);
    }

    // Phương thức tính số lượng trung bình của RAM hiện có trong kho
    public void calculateAverageQuantity() {
        // Lọc danh sách các RAM đang hoạt động
        List<RAMItem> activeItems = ramItems.stream()
                .filter(RAMItem::isActive) // Chỉ lấy các item đang hoạt động
                .collect(Collectors.toList());

        // Kiểm tra xem có RAM nào đang hoạt động hay không
        if (activeItems.isEmpty()) {
            System.out.println("Không có RAM item nào đang hoạt động.");
            return; // Không thực hiện tính toán nếu không có RAM hoạt động
        }

        // Tính tổng số lượng của các RAM đang hoạt động
        int totalQuantity = activeItems.stream()
                .mapToInt(RAMItem::getQuantity) // Chuyển thành các số lượng của RAM
                .sum(); // Tính tổng số lượng

        // Tính số lượng trung bình bằng cách chia tổng cho số lượng RAM đang hoạt động
        double averageQuantity = (double) totalQuantity / activeItems.size();

        // Hiển thị số lượng trung bình cho người dùng
        System.out.printf("Số lượng trung bình của RAM đang hoạt động: %.1f%n", averageQuantity);
    }

    // Phương thức hiển thị ngày sản xuất cũ nhất và mới nhất của RAM đang hoạt động
    public void showOldestAndNewestProductionDate() {
        // Lọc danh sách các RAM đang hoạt động
        List<RAMItem> activeItems = ramItems.stream()
                .filter(RAMItem::isActive) // Chỉ lấy các item đang hoạt động
                .collect(Collectors.toList());

        // Kiểm tra xem có RAM nào đang hoạt động hay không
        if (activeItems.isEmpty()) {
            System.out.println("Không có RAM item nào đang hoạt động.");
            return; // Không thực hiện tính toán nếu không có RAM hoạt động
        }

        // Sử dụng DateTimeFormatter để định dạng ngày tháng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

        // Tìm ngày sản xuất cũ nhất
        String oldestDate = activeItems.stream()
                .map(RAMItem::getProductionMonthYear) // Lấy ngày sản xuất dưới dạng chuỗi
                .min(Comparator.comparing(date -> YearMonth.parse(date, formatter))) // Tìm ngày cũ nhất
                .orElse("Không có ngày hợp lệ");

        // Tìm ngày sản xuất mới nhất
        String newestDate = activeItems.stream()
                .map(RAMItem::getProductionMonthYear) // Lấy ngày sản xuất dưới dạng chuỗi
                .max(Comparator.comparing(date -> YearMonth.parse(date, formatter))) // Tìm ngày mới nhất
                .orElse("Không có ngày hợp lệ");

        // Hiển thị ngày sản xuất cũ nhất và mới nhất cho người dùng
        System.out.println("Ngày sản xuất cũ nhất: " + oldestDate);
        System.out.println("Ngày sản xuất mới nhất: " + newestDate);
    }

    // Phương thức tạo báo cáo tổng hợp
    public void generateSummaryReport() {
        // Kiểm tra xem có RAM nào đang hoạt động hay không trước khi tính toán
        if (ramItems.stream().noneMatch(RAMItem::isActive)) {
            System.out.println("Không có RAM item nào đang hoạt động.");
            return;
        }

        System.out.println("=============== BÁO CÁO TỔNG HỢP ===============");

        // Tính và hiển thị tổng số lượng RAM đang hoạt động
        calculateTotalQuantity();

        // Tính và hiển thị số lượng nhỏ nhất và lớn nhất
        calculateMinMaxQuantity();

        // Tính và hiển thị số lượng trung bình
        calculateAverageQuantity();

        // Hiển thị ngày sản xuất cũ nhất và mới nhất
        showOldestAndNewestProductionDate();

        System.out.println("=================================================");
    }







    // Tính Tổng Giá Trị Tồn Kho Theo Thương Hiệu
    public void calculateTotalInventoryValueByBrand() {
        // Map để lưu trữ tổng giá trị tồn kho theo thương hiệu
        Map<String, Double> inventoryValueByBrand = new HashMap<>();

        for (RAMItem item : ramItems) {
            if (item.isActive()) { // Chỉ tính cho các RAM đang hoạt động
                double totalValue = item.getQuantity() * item.getPrice(); // Tính giá trị tồn kho của RAM
                inventoryValueByBrand.merge(item.getBrand(), totalValue, Double::sum); // Cộng dồn giá trị theo thương hiệu
            }
        }

        // Hiển thị tổng giá trị tồn kho theo từng thương hiệu
        System.out.println("Tổng giá trị tồn kho theo thương hiệu:");
        inventoryValueByBrand.forEach((brand, totalValue) -> System.out.printf("%-10s: Tổng giá trị tồn kho: %.2f%n", brand, totalValue));
    }

    // Phương thức tính số ngày tồn kho trung bình của các RAM đang hoạt động
    public void calculateAverageDaysInInventory() {
        // Lọc danh sách các RAM đang hoạt động
        List<RAMItem> activeItems = ramItems.stream()
                .filter(RAMItem::isActive)
                .collect(Collectors.toList());

        if (activeItems.isEmpty()) {
            System.out.println("Không có RAM item nào đang hoạt động.");
            return;
        }

        // Tính tổng số ngày tồn kho của tất cả các RAM đang hoạt động
        long totalDays = activeItems.stream()
                .mapToLong(RAMItem::getDaysInInventory)
                .sum();

        // Tính trung bình số ngày tồn kho
        double averageDays = (double) totalDays / activeItems.size();

        // Hiển thị kết quả
        System.out.printf("Số ngày tồn kho trung bình của các RAM đang hoạt động: %.0f ngày%n", averageDays);
    }

    public void listLowInventoryItems() {
        Scanner sc = new Scanner(System.in);

        // Yêu cầu người dùng nhập ngưỡng tồn kho
        System.out.print("Nhập ngưỡng tồn kho để kiểm tra các mục có số lượng thấp hơn: ");
        int threshold;
        try {
            threshold = sc.nextInt();
            if (threshold <= 0) {
                System.out.println("Ngưỡng tồn kho phải là số dương. Vui lòng thử lại.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Ngưỡng tồn kho không hợp lệ. Vui lòng nhập một số nguyên.");
            return;
        }

        // Lọc các mục có số lượng thấp hơn ngưỡng người dùng đã nhập
        List<RAMItem> lowInventoryItems = ramItems.stream()
                .filter(item -> item.isActive() && item.getQuantity() < threshold)
                .collect(Collectors.toList());

        // Kiểm tra danh sách có mục nào dưới ngưỡng không
        if (lowInventoryItems.isEmpty()) {
            System.out.println("Không có mục nào có số lượng thấp hơn: " + threshold);
        } else {
            // Chỉ in tiêu đề khi có mục trong danh sách
            System.out.println("Các mục có số lượng thấp hơn: " + threshold);
            System.out.printf("%-12s | %-10s | %-11s | %-15s | %-10s%n", "Mã","Loại","Thương hiệu","Ngày sản xuất","Số lượng");

            // In danh sách các mục tồn kho thấp
            lowInventoryItems.forEach(item -> System.out.printf("%-12s | %-10s | %-1s | %-15s | %-10d%n",
                    item.getCode(), item.getType(), item.getBrand(), item.getProductionMonthYear(), item.getQuantity()));
        }
    }

    public void calculateTotalQuantityByYear() {
        // Nhóm các RAM theo năm sản xuất và tính tổng số lượng của từng năm
        Map<String, Integer> quantityByYear = ramItems.stream()
                .filter(RAMItem::isActive) // Lọc chỉ các mục đang hoạt động
                .collect(Collectors.groupingBy(
                        item -> item.getProductionMonthYear().substring(3), // Lấy năm từ MM/YYYY
                        Collectors.summingInt(RAMItem::getQuantity) // Tính tổng số lượng theo năm
                ));

        // Kiểm tra danh sách có mục nào không
        if (quantityByYear.isEmpty()) {
            System.out.println("Không có mục RAM nào để tính số lượng theo năm.");
        } else {
            System.out.println("Tổng số lượng RAM theo năm sản xuất:");
            System.out.printf("%-10s | %-10s%n", "Năm", "Số Lượng");

            // Hiển thị kết quả theo năm sản xuất
            quantityByYear.forEach((year, totalQuantity) -> System.out.printf("%-10s | %-10d%n", year, totalQuantity));
        }
    }

    public void findMostPopularBrand() {
        // Nhóm các RAM theo thương hiệu và tính tổng số lượng của từng thương hiệu
        Map<String, Integer> quantityByBrand = ramItems.stream()
                .filter(RAMItem::isActive) // Lọc chỉ các mục đang hoạt động
                .collect(Collectors.groupingBy(
                        RAMItem::getBrand, // Nhóm theo thương hiệu
                        Collectors.summingInt(RAMItem::getQuantity) // Tính tổng số lượng theo thương hiệu
                ));

        // Kiểm tra nếu danh sách rỗng
        if (quantityByBrand.isEmpty()) {
            System.out.println("Không có mục RAM nào trong kho để xác định thương hiệu phổ biến nhất.");
            return;
        }

        // Tìm thương hiệu có số lượng lớn nhất
        Map.Entry<String, Integer> mostPopularBrand = quantityByBrand.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // So sánh theo số lượng để tìm giá trị lớn nhất
                .orElse(null);

        // Hiển thị thương hiệu phổ biến nhất
        if (mostPopularBrand != null) {
            System.out.println("Thương hiệu RAM phổ biến nhất:");
            System.out.printf("%s: Số lượng: %d%n", mostPopularBrand.getKey(), mostPopularBrand.getValue());
        }
    }

    public void calculateRAMUsagePercentageByType() {
        // Tổng số lượng tất cả các RAM đang hoạt động trong kho
        int totalQuantity = ramItems.stream()
                .filter(RAMItem::isActive)
                .mapToInt(RAMItem::getQuantity)
                .sum();

        // Kiểm tra nếu không có RAM trong kho
        if (totalQuantity == 0) {
            System.out.println("Không có RAM nào trong kho để tính tỉ lệ sử dụng.");
            return;
        }

        // Nhóm và tính tổng số lượng theo từng loại RAM
        Map<String, Integer> quantityByType = ramItems.stream()
                .filter(RAMItem::isActive)
                .collect(Collectors.groupingBy(
                        RAMItem::getType, // Nhóm theo loại RAM
                        Collectors.summingInt(RAMItem::getQuantity) // Tính tổng số lượng theo loại
                ));

        // Hiển thị tỉ lệ sử dụng của từng loại RAM
        System.out.println("Tỉ lệ sử dụng RAM theo từng loại:");
        System.out.printf("%-10s | %-10s%n", "Loại", "Tỉ lệ (%)");

        quantityByType.forEach((type, quantity) -> {
            double percentage = ((double) quantity / totalQuantity) * 100;
            System.out.printf("%-10s | %-10.2f%n", type, percentage);
        });
    }











    
    
    
    
}

