package mvc;


import java.util.ArrayList;

public class ballController {
    private ballModel model;
    private ballView view;
    private ArrayList<mvc_balls> balls = new ArrayList<>();



    public ballController() {
        model = new ballModel();
        view = new ballView();


    }

}
