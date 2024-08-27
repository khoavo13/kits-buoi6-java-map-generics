import java.util.*;
import java.util.stream.Collectors;
public class Main {
    public static void test1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("USA", "Washington, D.C");
        map.put("France", "Paris");
        map.put("Japan", "Tokyo");

        // Kiem tra su ton tai cua mot khoa
        if (map.containsKey("France")) {
            System.out.println("Capital of France: " + map.get("France"));
        }

        // Kiem tra su ton tai cua mot gia tri
        if (map.containsValue("Tokyo")) {
            System.out.println("Tokyo is in the map.");
        }

        // Xoa mot phan tu
        map.remove("Japan");
        System.out.println("##################");

        // In ra cac phan tu con lai trong HashMap
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
        System.out.println("##################");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Country: " + key + ", Capital: " + value);
        }
        System.out.println("##################");
        // Lay gia tri voi khoa co san
        System.out.println("Value of key 'USA': " + map.getOrDefault("USA", "Not found"));
        // Lay gia tri voi khoa khong co san
        System.out.println("Value of key 'C': " + map.getOrDefault("C", "Not found"));
        // Them vao hashmap. Neu key khong ton tai se duoc them moi, neu key da ton tai thi value duoc update
        map.putIfAbsent("VietNam", "Ha Noi");
        System.out.println("Value of Vietnam " + map.get("VietNam"));
        // Update value neu key chua trong hashmap. Nếu value truoc do là null thì có thể gây ra NullpointerException
        map.computeIfPresent("France", (k, v) -> v + " - update");
        System.out.println("Update France capital: " + map.get("France"));
    }

    public static void test2_countWords(){
        String s = "xin chao thay meo thay tho thay gau di choi pho khap pho phuong";
        HashMap<String, Integer> map = new HashMap<>();
        String[] words = s.split(" ");
        for (String x : words){
            int count = map.getOrDefault(x, 0);
            map.put(x, count + 1);
        }
        for (String key : map.keySet()){
            System.out.println(key + ": " + map.get(key));
        }
    }

    public static void test3_countWords(){
        String s = "xin chao thay meo thay tho thay gau di choi pho khap pho phuong";
        HashMap<String, Integer> map = new HashMap<>();
        StringTokenizer tokenizer  = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()){
            String word = tokenizer.nextToken();
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String key : map.keySet()){
            System.out.println(key + ": " + map.get(key));
        }
    }

    public static void test4_dictionary(){
        HashMap<String, String> dictionary = new HashMap<>();

        dictionary.put("apple", "a round fruit with shiny red or green skin that is fairly hard and white inside");
        dictionary.put("banana", "a long curved fruit with a thick yellow skin and that is soft inside, which grows on trees in hot countries");
        dictionary.put("cherry", "a small soft round fruit with shiny red or black skin and a large seed inside");

        Scanner sc = new Scanner(System.in);
        System.out.println("Moi ban nhap tu can tra cuu: ");
        String name = sc.nextLine();
        String res = dictionary.get(name);
        if (res != null){
            System.out.println(name + " : " + res);
        }
        else {
            System.out.println("Not found");
        }
    }

    public static void test5_stream(){
        HashMap<String, Integer> map = new HashMap<>();

        // Quoc gia - so luong cong ty co von tren 10M do
        map.put("USA", 3);
        map.put("France", 7);
        map.put("Japan", 2);
        map.put("VietNam", 25);

        System.out.println("##################");
        map.entrySet().stream()
                .forEach(item -> System.out.println(item.getKey() + ":" + item.getValue()));

        // Tinh tong so luong cac cong ty tren
        System.out.println("##################");
        int kq = map.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Sum: " + kq);

        // Loc cac quoc gia co so luong cong ty lon hon 5
        Map<String, Integer> newMap = map.entrySet().stream()
                .filter(item -> item.getValue() > 5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(newMap);
    }

    public static void test_linkedhashmap(){
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // Them phan tu
        linkedHashMap.put("one", 1);
        linkedHashMap.put("two", 2);
        linkedHashMap.put("three", 3);

        // Cap nhat gia tri cua khoa two
        linkedHashMap.put("two", 22);

        // Truy cap vao phan tu
        linkedHashMap.get("one");

        // Xoa phan tu voi key
        linkedHashMap.remove("three");

        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void test_treemap(){
        // Tao 1 map tree voi Comparator tuy chinh de sap xep theo thu tu giam dan
        TreeMap<String, Integer>  treeMap = new TreeMap<>(Comparator.reverseOrder());

        treeMap.put("banana", 2);
        treeMap.put("apple", 3);
        treeMap.put("cherry", 1);

        // In re TreeMap
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Tao TreeMap voi khoa la ten va gia tri la tuoi
        TreeMap<String, Integer> ageMap = new TreeMap<>();

        // Them cac phan tu TreeMap
        ageMap.put("John", 30);
        ageMap.put("Jane", 25);
        ageMap.put("Paul", 35);
        ageMap.put("Anna", 28);

        System.out.println("###############");
        for (Map.Entry<String, Integer> entry : ageMap.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Tim kiem cac phan tu
        System.out.println("First key: " + ageMap.firstKey()); // ten dau tien theo thu tu
        System.out.println("Last key: " + ageMap.lastKey());
        System.out.println("Sub map from 'Anna' to 'Paul': " + ageMap.subMap("Anna", "Paul"));

    }

    // test hashCode
    public static void test_hashCode_point(){
        Map<Point, String> pointMap = new HashMap<>();
        Point p1 = new Point(1,2);
        Point p2 = new Point(1,2); // Object Point giong p1
        Point p3 = new Point(3,4);

        pointMap.put(p1, "Point 1");
        pointMap.put(p3, "Point 2");

        // In ra ket qua kiem tra
        System.out.println("Map contains p2: " + pointMap.containsKey(p2)); // true vif p1 = p2
        System.out.println("Map content: "+ pointMap);
    }

    public static void test_hashCode_person(){
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);
        Person p3 = new Person("Bob", 25);

        // Su dung HashMap de kiem tra equals va hashCode
        Map<Person, Integer> map = new HashMap<>();
        map.put(p1, 1);
        map.put(p3,3);

        // In ra ket qua kiem tra
        System.out.println("Map contains p2: " + map.containsKey(p2));
        System.out.println("Map content: " + map);
    }
    public static void test_optional() {
        // Tao cac doi tuong Optional
        Optional<String> optionalValue = Optional.of("Hello, World!");
        Optional<String> emptyOptional = Optional.empty();

        // Su dung cac phuong thuc cua Optional
        System.out.println("Optional Value is present: " + emptyOptional.isPresent());
        optionalValue.ifPresent(value -> System.out.println("Value: " + value));

        // Lay gia tri hoac gia tri thay the
        String value = emptyOptional.orElse("Default Value");
        System.out.println("Value or Defailt: " + value);

        // Gia tri thay the tu Supplier
        String generatedValue = emptyOptional.orElseGet(() -> "Generated default value");
        System.out.println("Generated value: " + generatedValue);

        // Gia tri tu Optional neu co, hoac nem ngoai le neu khong co
        try {
            String result = emptyOptional.orElseThrow(() -> new IllegalArgumentException("Value not present"));
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    public static <T> void printArray(T[] array){
        for (T element : array){
            System.out.print(element + " ");
        }
        System.out.println("");
    }

    // Generic
    public static void generic1(){
        // Tao box cho String
        Box<String> stringBox = new Box();
        stringBox.setContent("Hello Generics");
        System.out.println("String content: " + stringBox.getContent());

        // Tao box cho Integer
        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(123);
        System.out.println("Integer content: " + stringBox.getContent());

        // Goi phuong thuc Generic
        Integer[] intArray = {1, 2, 3, 4};
        String[]  stringArray = {"A", "B", "C"};
        printArray(intArray);
        printArray(stringArray);

        // Tao danh sach cho so nguyen
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        // In danh sach
        for (Integer item : integerList){
            System.out.println(item);
        }

        Pair<String, Integer> pair = new OrderedPair<>("Age", 23);
        System.out.println("Key: " + pair.getKey() + ", value: " + pair.getValue());
    }
    public static void main(String[] args) {
        generic1();
        int[] a = {1, 2, 3, 4};
        System.out.println(3/6);
    }

}
