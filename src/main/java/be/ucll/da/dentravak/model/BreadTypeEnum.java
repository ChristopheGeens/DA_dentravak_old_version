package be.ucll.da.dentravak.model;

public enum  BreadTypeEnum {
    TURKISHBREAD("Turks brood"),
    WRAP("Wrap"),
    BOTERHAMMEKES("Boterhammekes");

    private String type;

    BreadTypeEnum(String type){
        this.type=type;
    }

    public String getType(){
        return type;
    }
}
