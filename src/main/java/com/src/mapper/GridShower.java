package com.src.mapper;

public class GridShower {


    public void showGrid (GridMapper grid) {
        System.out.print("  ");
        for (int i = 0; i < 5; i++) {
            System.out.print("  " + (i+1) + "  ");
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    System.out.println("  ");
                    System.out.print(i+1 + " ");
                }
                System.out.print("| " + grid.getGrid().get(i).get(j).getTerrain().getDescription() + " |");
            }
            System.out.println();
        }
    }

}
