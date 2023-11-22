package com.miwis.tabnewskt.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.miwis.tabnewskt.R

val roboto = FontFamily(
        Font(R.font.roboto_mono),
        Font(R.font.roboto_mono, FontWeight.W500),
        Font(R.font.roboto_mono, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
        bodyLarge = TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
        ),
        titleMedium = TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.sp
        ),
        bodyMedium = TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                letterSpacing = 0.25.sp
        ),
        titleLarge = TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.sp
        ),
        /* Other default text styles to override
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)