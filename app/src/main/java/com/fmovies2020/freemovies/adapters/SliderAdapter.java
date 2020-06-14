package com.fmovies2020.freemovies.adapters;


import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.github.islamkhsh.CardSliderAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.fmovies2020.freemovies.DetailsActivity;
import com.fmovies2020.freemovies.LoginActivity;
import com.fmovies2020.freemovies.R;
import com.fmovies2020.freemovies.WebViewActivity;
import com.fmovies2020.freemovies.models.home_content.Slide;
import com.fmovies2020.freemovies.utils.PreferenceUtils;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static com.fmovies2020.freemovies.utils.MyAppClass.getContext;

public class SliderAdapter extends CardSliderAdapter<Slide> {

    public SliderAdapter(@NotNull ArrayList<Slide> items) {
        super(items);
    }

    @Override
    public void bindView(int i, @NotNull View view, @Nullable final Slide slide) {
        if (slide != null){
            TextView textView = view.findViewById(R.id.textView);

            textView.setText(slide.getTitle());
            RoundedImageView imageView = view.findViewById(R.id.imageview);
            Picasso.get().load(slide.getImageLink()).into(imageView);
            View lyt_parent = view.findViewById(R.id.lyt_parent);
            lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (slide.getActionType().equalsIgnoreCase("tvseries") || slide.getActionType().equalsIgnoreCase("movie")){
                        if (PreferenceUtils.isMandatoryLogin(getContext())){
                            if (PreferenceUtils.isLoggedIn(getContext())){
                                Intent intent=new Intent(getContext(), DetailsActivity.class);
                                intent.putExtra("vType",slide.getActionType());
                                intent.putExtra("id",slide.getActionId());

                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                getContext().startActivity(intent);
                            }else {
                                getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                            }
                        }else {
                            Intent intent=new Intent(getContext(), DetailsActivity.class);
                            intent.putExtra("vType",slide.getActionType());
                            intent.putExtra("id",slide.getActionId());

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            getContext().startActivity(intent);
                        }

                    }else if (slide.getActionType().equalsIgnoreCase("webview")){
                        Intent intent = new Intent(getContext(), WebViewActivity.class);
                        intent.putExtra("url", slide.getActionUrl());

                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getContext().startActivity(intent);

                    }else if (slide.getActionType().equalsIgnoreCase("external_browser")){
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(slide.getActionUrl()));

                        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getContext().startActivity(browserIntent);

                    }else if (slide.getActionType().equalsIgnoreCase("tv")){
                        if (PreferenceUtils.isMandatoryLogin(getContext())){
                            if (PreferenceUtils.isLoggedIn(getContext())){
                                Intent intent=new Intent(getContext(), DetailsActivity.class);
                                intent.putExtra("vType",slide.getActionType());
                                intent.putExtra("id",slide.getActionId());

                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                getContext().startActivity(intent);
                            }else {
                                getContext().startActivity(new Intent(getContext(), LoginActivity.class));
                            }
                        }else {
                            Intent intent=new Intent(getContext(), DetailsActivity.class);
                            intent.putExtra("vType",slide.getActionType());
                            intent.putExtra("id",slide.getActionId());

                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            getContext().startActivity(intent);
                        }
                    }
                }
            });
        }
    }


    @Override
    public int getItemContentLayout(int i) {
        return R.layout.slider_item;
    }
}
