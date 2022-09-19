package com.jppedrosa.portrayal.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jppedrosa.portrayal.data.remote.dto.Image
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 19/09/2022.
 */
@Composable
fun ImageListItem(
    image: Image,
    onItemClick: (Image) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(image) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GlideImage(
            imageModel = image.urls.regular,
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        )
        /*Text(
            text = image.description,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )*/
    }
}
