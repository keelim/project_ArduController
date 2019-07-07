package com.keelim.arducon;

import android.app.Activity;
import android.content.Intent;
import android.widget.FrameLayout;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class Helper {

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
                .addGooglePlayStoreLink("8253364315283647351")
                .addGitHubLink("keelim")
                .addEmailLink("kimh00335@gmail.com")
                .addFiveStarsAction()
                .addMoreFromMeAction("Keelim")
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .addUpdateAction()
                .setActionsColumnsCount(2)
                .addFeedbackAction("kimh00335@gmail.com")
                .addPrivacyPolicyAction("http")
                .addIntroduceAction((Intent) null)
                .addHelpAction((Intent) null)
                .addChangeLogAction((Intent) null)
                .addDonateAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        flHolder.addView(view);
    }


}
