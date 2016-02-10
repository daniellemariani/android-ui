package com.dmariani.androidui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmariani.androidui.R;
import com.dmariani.androidui.model.Article;
import com.squareup.picasso.Picasso;

/**
 * @author Danielle Mariani on 10/02/16.
 */
public class ArticleFragment extends BaseFragment {

    /**
     * Constants
     */
    private static final String ARG_ARTICLE = "article";
    private static final String ARG_NESTED_SCROLL = "nested_scroll";

    /**
     * Views
     */
    private TextView textViewTitle;
    private TextView textViewDescription;
    private ImageView imageView;
    private TextView textViewImage;

    /**
     * Attribute
     */
    private Article article;

    public static ArticleFragment newInstance(Article article, boolean nestedScroll) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_ARTICLE, article);
        bundle.putBoolean(ARG_NESTED_SCROLL, nestedScroll);
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean nestedScroll = getArguments().getBoolean(ARG_NESTED_SCROLL, false);
        if (nestedScroll) {
            return inflater.inflate(R.layout.fragment_article_nested_scroll_, container, false);
        } else {
            return inflater.inflate(R.layout.fragment_article, container, false);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewTitle = (TextView) getView().findViewById(R.id.textview_title);
        textViewDescription = (TextView) getView().findViewById(R.id.textview_description);
        imageView = (ImageView) getView().findViewById(R.id.imageview_article);
        textViewImage = (TextView) getView().findViewById(R.id.textview_image_legend);
        if (getArguments() != null) {
            article = (Article) getArguments().getSerializable(ARG_ARTICLE);
            textViewTitle.setText(article.getLongTitle());
            textViewDescription.setText(article.getDescription());
            textViewImage.setText(article.getImageLegend());
            Picasso.with(getActivity()).load(article.getImageUrl()).into(imageView);
        }

    }
}
