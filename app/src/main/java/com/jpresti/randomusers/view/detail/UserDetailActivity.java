package com.jpresti.randomusers.view.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jpresti.randomusers.R;
import com.jpresti.randomusers.view.users.UserGridActivity;

/**
 * An activity representing a single User detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a grid of items
 * in a {@link UserGridActivity}.
 */
public class UserDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USER = "EXTRA_USER";

    public static Intent getStartIntent(Context context, String userJson) {
        Intent intent = new Intent(context, UserDetailActivity.class);
        intent.putExtra(EXTRA_USER, userJson);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Toolbar toolbar = findViewById(R.id.dt_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /* If savedInstanceState != null,
         * the fragment will automatically be re-added to its container
         * */
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity using a fragment transaction.
            UserDetailFragment fragment =
                    UserDetailFragment.newInstance(getIntent().getStringExtra(EXTRA_USER));
            getSupportFragmentManager().beginTransaction().add(R.id.dt_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, UserGridActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
