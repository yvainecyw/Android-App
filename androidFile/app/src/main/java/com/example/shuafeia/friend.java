package com.example.shuafeia;

/**
 * Data model for each row of the RecyclerView
 */
class friend {

    // Member variables representing the title and information about the sport.
    private String name;
    private final int imageResource;
    private int num;

    friend(String name,int imageResource,int num) {
        this.name = name;
        this.imageResource = imageResource;
        this.num = num ;
    }
    public int getImageResource() {return imageResource;}

    public String getName() {
        return name;
    }

    public int getNum()
    {
        return  num;
    }

}
