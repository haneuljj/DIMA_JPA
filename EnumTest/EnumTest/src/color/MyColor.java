package color;

public enum MyColor {
    빨강("RED"), 주황("ORANGE"), 노랑("YELLOW"), 
    초록("GREEN"), 파랑("BLUE"), 남색("DARKBLUE"), 보라("PURPLE");

    // 단어를 두 개씩 맵핑 시킬때
    private String color;
    // 색깔 값을 받아 우리멤버로 붙이는 것처럼
    private MyColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
}
