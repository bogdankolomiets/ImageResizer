package com.example.imageresizer;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 02.07.16
 */
public class ImageResizer {
  private static float sCoeficient;
  private static int sBasicWidth;

  private ImageResizer() {

  }

  public static void setBasicWidth(int width) {
    sBasicWidth = width;
  }

  public static void with(Activity activity) {
    sCoeficient = getCoeficient(activity);
  }

  public static int resize(int pixelSize) {
    return (int) (sCoeficient * pixelSize);
  }

  public static void configureView(View view, int width, int height) {
    RelativeLayout.LayoutParams params =
        new RelativeLayout.LayoutParams((int) (sCoeficient * width), (int) (sCoeficient * height));

    view.setLayoutParams(params);
  }

  public static void setMargin(View view, int margin) {
    setPosition(view, margin, margin, margin, margin);
  }

  public static void setPosition(View view, int leftMargin, int topMargin) {
    setPosition(view, leftMargin, topMargin, 0, 0);
  }

  public static void setPosition(View view, int leftMargin, int topMargin,
                                 int rightMargin, int bottomMargin) {
    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
    params.leftMargin = (int) (leftMargin * sCoeficient);
    params.topMargin = (int) (topMargin * sCoeficient);
    params.rightMargin = (int) (rightMargin * sCoeficient);
    params.bottomMargin = (int) (bottomMargin * sCoeficient);

    view.setLayoutParams(params);
  }

  public static void setPadding(View view, int leftPadding, int topPadding,
                                int rightPadding, int bottomPadding) {
    view.setPadding((int) (leftPadding * sCoeficient), (int) (topPadding * sCoeficient),
        (int) (rightPadding * sCoeficient), (int) (bottomPadding * sCoeficient));
  }

  private static float getCoeficient(Activity activity) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    int screenWidth = displayMetrics.widthPixels;

    return screenWidth / sBasicWidth;
  }


}
