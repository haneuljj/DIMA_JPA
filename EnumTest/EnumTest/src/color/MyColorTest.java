package color;

public class MyColorTest {
    public static void main(String[] args) {
        // 전체 데이터를 배열로 반환
        MyColor[] colors = MyColor.values(); // static method 처럼 동작

        for(MyColor c : colors) System.out.println(c);
        System.out.println();

        MyColor c = MyColor.valueOf("빨강"); // 문자열을 전달하면 enum타입의 데이터 반환
        System.out.println(c);

        System.out.println(c.getColor());
        System.out.println(MyColor.valueOf("노랑").getColor());

        System.out.println();
        int index = MyColor.valueOf("빨강").ordinal(); // 가져온 enum객체의 위치값 반환
        System.out.println("빨강의 위치는 " + index);

        System.out.println();
        MyColor c2 = MyColor.valueOf("보라");

        // switch case문에 문자형, 정수형 말고 enum타입도 넣을 수 있음
        switch (c2) {
            case 빨강:
                System.out.println("빨강을 선택했습니다.");
                break;
            case 주황:
                System.out.println("주황을 선택했습니다.");
                break;
            case 노랑:
                System.out.println("노랑을 선택했습니다.");
                break;
            case 초록:
                System.out.println("초록을 선택했습니다.");
                break;
            case 파랑:
                System.out.println("파랑을 선택했습니다.");
                break;
            case 남색:
                System.out.println("남색을 선택했습니다.");
                break;
            case 보라:
                System.out.println("보라을 선택했습니다.");
                break;
        }
    }
}
