package com.creedeon.creed003;


import android.content.Context;
import android.graphics.Color;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import org.jetbrains.annotations.NotNull;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;


    public int[] lst_images = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3

    };
    // list of titles
    public String[] lst_title = {
            "COSMONAUT",
            "SATELITE",
            "GALAXY"
    }   ;

    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.rgb(49,49,49),
            Color.rgb(49,49,49),
            Color.rgb(49,49,49)
    };


    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view== object);
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = view.findViewById(R.id.slideimg);
        //TextView txttitle= view.findViewById(R.id.txttitle);
        //TextView description = view.findViewById(R.id.txtdescription);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        //txttitle.setText(lst_title[position]);
        //description.setText(lst_description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
