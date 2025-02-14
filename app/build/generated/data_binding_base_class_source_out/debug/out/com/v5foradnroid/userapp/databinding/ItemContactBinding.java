// Generated by view binder compiler. Do not edit!
package com.v5foradnroid.userapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.v5foradnroid.userapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemContactBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView contactName;

  @NonNull
  public final TextView contactNumber;

  @NonNull
  public final ImageView contactPhoto;

  @NonNull
  public final TextView drawableTextView;

  @NonNull
  public final Guideline guideline;

  @NonNull
  public final ConstraintLayout selectedContactLayout;

  private ItemContactBinding(@NonNull CardView rootView, @NonNull TextView contactName,
      @NonNull TextView contactNumber, @NonNull ImageView contactPhoto,
      @NonNull TextView drawableTextView, @NonNull Guideline guideline,
      @NonNull ConstraintLayout selectedContactLayout) {
    this.rootView = rootView;
    this.contactName = contactName;
    this.contactNumber = contactNumber;
    this.contactPhoto = contactPhoto;
    this.drawableTextView = drawableTextView;
    this.guideline = guideline;
    this.selectedContactLayout = selectedContactLayout;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemContactBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemContactBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_contact, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemContactBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.contact_name;
      TextView contactName = ViewBindings.findChildViewById(rootView, id);
      if (contactName == null) {
        break missingId;
      }

      id = R.id.contact_number;
      TextView contactNumber = ViewBindings.findChildViewById(rootView, id);
      if (contactNumber == null) {
        break missingId;
      }

      id = R.id.contact_photo;
      ImageView contactPhoto = ViewBindings.findChildViewById(rootView, id);
      if (contactPhoto == null) {
        break missingId;
      }

      id = R.id.drawableTextView;
      TextView drawableTextView = ViewBindings.findChildViewById(rootView, id);
      if (drawableTextView == null) {
        break missingId;
      }

      id = R.id.guideline;
      Guideline guideline = ViewBindings.findChildViewById(rootView, id);
      if (guideline == null) {
        break missingId;
      }

      id = R.id.selectedContact_layout;
      ConstraintLayout selectedContactLayout = ViewBindings.findChildViewById(rootView, id);
      if (selectedContactLayout == null) {
        break missingId;
      }

      return new ItemContactBinding((CardView) rootView, contactName, contactNumber, contactPhoto,
          drawableTextView, guideline, selectedContactLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
