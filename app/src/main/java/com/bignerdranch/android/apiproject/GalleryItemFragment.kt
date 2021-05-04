package com.bignerdranch.android.apiproject

import android.animation.ObjectAnimator
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import java.util.*


private const val ARG_GALLERY_ITEM_NAME = "gallery_item_name"
private const val ARG_GALLERY_ITEM_IMAGE = "gallery_item_image"

private const val ARG_GALLERY_ITEM_STATUS = "gallery_item_status"
private const val ARG_GALLERY_ITEM_GENDER = "gallery_item_gender"
private const val ARG_GALLERY_ITEM_SPECIES = "gallery_item_species"

class GalleryItemFragment: Fragment() {

    private lateinit var nameTextView: TextView
    private lateinit var imageView: ImageView

    private lateinit var statusTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var speciesTextView: TextView

    private lateinit var siteButton: Button
    private lateinit var phoneButton: Button

    private lateinit var galleryItemName: String
    private lateinit var galleryItemImage: String

    private lateinit var galleryItemStatus: String
    private lateinit var galleryItemGender: String
    private lateinit var galleryItemSpecies: String

    private lateinit var sceneView:View
    private lateinit var sunView: View
    private lateinit var skyView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        galleryItemName = arguments?.getSerializable(ARG_GALLERY_ITEM_NAME) as String
        galleryItemImage = arguments?.getSerializable(ARG_GALLERY_ITEM_IMAGE) as String

        galleryItemStatus = arguments?.getSerializable(ARG_GALLERY_ITEM_STATUS) as String
        galleryItemGender = arguments?.getSerializable(ARG_GALLERY_ITEM_GENDER) as String
        galleryItemSpecies = arguments?.getSerializable(ARG_GALLERY_ITEM_SPECIES) as String


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery_item, container, false)

        nameTextView = view.findViewById(R.id.nameTextView)

        imageView = view.findViewById(R.id.imageView)

        statusTextView = view.findViewById(R.id.statusTextView)
        genderTextView = view.findViewById(R.id.genderTextView)
        speciesTextView = view.findViewById(R.id.speciesTextView)

        siteButton = view.findViewById(R.id.open_game) as Button
        phoneButton = view.findViewById(R.id.call_phone) as Button

        sceneView = view.findViewById(R.id.scene)
        sunView = view.findViewById(R.id.sun)
        skyView = view.findViewById(R.id.sky)

        sceneView.setOnClickListener{
            sunView.visibility = View.VISIBLE
            startAnimation()
        }

        return view
    }

    private fun startAnimation() {
        val sunYStart = skyView.left.toFloat() - sunView.width.toFloat() - 70
        val sunYEnd = skyView.right.toFloat()

        val heightAnimator = ObjectAnimator
                .ofFloat(sunView, "x",sunYStart,sunYEnd)
                .setDuration(3000)

        heightAnimator.start()
    }

    override fun onStart() {
        super.onStart()

        siteButton.setOnClickListener{

//          open the website
            val site = Uri.parse("https://www.adultswim.com/videos/rick-and-morty")
            val siteIntent = Intent(Intent.ACTION_VIEW, site)
            startActivity(siteIntent)
        }
        phoneButton.setOnClickListener{
            // call the phonenumber
            val number = Uri.parse("tel:5551234")
            val phoneIntent = Intent(Intent.ACTION_DIAL, number)
            startActivity(phoneIntent)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTextView.text = galleryItemName
        Picasso.get().load(galleryItemImage).into(imageView)
        genderTextView.text = galleryItemGender
        speciesTextView.text = galleryItemSpecies
        statusTextView.text = galleryItemStatus
    }

    companion object {
        fun newInstance(galleryItem: GalleryItem): GalleryItemFragment {
            val fragment = GalleryItemFragment()

            val arguments = Bundle()
            arguments.putSerializable(ARG_GALLERY_ITEM_NAME, galleryItem.name)
            arguments.putSerializable(ARG_GALLERY_ITEM_IMAGE, galleryItem.image)
            arguments.putSerializable(ARG_GALLERY_ITEM_GENDER, galleryItem.gender)
            arguments.putSerializable(ARG_GALLERY_ITEM_SPECIES, galleryItem.species)
            arguments.putSerializable(ARG_GALLERY_ITEM_STATUS, galleryItem.status)
            fragment.arguments = arguments

            return fragment
        }
    }
}