package com.keelim.arducon;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class Helper implements View.OnClickListener {

    private Activity activity;
    private int theme = R.style.Theme_AppCompat_DayNight;

    private Helper(Activity activity) {
        this.activity = activity;
    }

    public static Helper with(Activity activity) {
        return new Helper(activity);
    }

    public Helper init() {
        activity.setTheme(theme);

        activity.findViewById(R.id.dark).setOnClickListener(this);
        activity.findViewById(R.id.light).setOnClickListener(this);
        activity.findViewById(R.id.custom).setOnClickListener(this);

        return this;
    }

    public void loadAbout() {
        final FrameLayout flHolder = activity.findViewById(R.id.about);

        AboutBuilder builder = AboutBuilder.with(activity)
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("Keelim")
                .setSubTitle("Mobile Developer")
                .setLinksColumnsCount(4)
                .setBrief("Mobile developer")
//                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("keelim")
//                .addBitbucketLink("jrvansuita")
//                .addFacebookLink("user")
//                .addTwitterLink("user")
//                .addInstagramLink("jnrvans")
//                .addGooglePlusLink("+JuniorVansuita")
//                .addYoutubeChannelLink("CaseyNeistat")
//                .addDribbbleLink("user")
//                .addLinkedInLink("arleu-cezar-vansuita-júnior-83769271")
                .addEmailLink("kimh00335@gmail.com")
//                .addWhatsappLink("Jr", "+554799650629")
                .addSkypeLink("user")
                .addGoogleLink("user")
                .addAndroidLink("user")
                .addWebsiteLink("site")
                .addFiveStarsAction()
                .addMoreFromMeAction("Keelim")
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .addUpdateAction()
                .setActionsColumnsCount(2)
                .addFeedbackAction("kimh00335@gmail.com")
//                .addPrivacyPolicyAction("http")
                .addIntroduceAction((Intent) null)
                .addHelpAction((Intent) null)
                .addChangeLogAction((Intent) null)
                .addRemoveAdsAction((Intent) null)
                .addDonateAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        flHolder.addView(view);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dark:
                if (theme != R.style.Theme_AppCompat_DayNight) {
                    theme = R.style.Theme_AppCompat_DayNight;
                    activity.recreate();
                }
                break;
            case R.id.light:
                if (theme != R.style.Theme_AppCompat_DayNight) {
                    theme = R.style.Theme_AppCompat_DayNight;
                    activity.recreate();
                }
                break;

            case R.id.custom:
                if (theme != R.style.Theme_AppCompat_DayNight) {
                    theme = R.style.Theme_AppCompat_DayNight;
                    activity.recreate();
                }
                break;

            default:
                break;
        }
    }
}
