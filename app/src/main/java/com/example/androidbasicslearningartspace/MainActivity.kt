package com.example.androidbasicslearningartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidbasicslearningartspace.ui.theme.AndroidBasicsLearningArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicsLearningArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}
//Дата класс для хранения данных
data class Gallery(val image: Int, val title: String, val artist: String, var year: String)

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {

    var currentIndex by remember { mutableStateOf(0) }

    val galleryList: ArrayList<Gallery> = arrayListOf() //Создаю список
    galleryList.add(Gallery(R.drawable.image01, "Петергоф", "Konstantn", "08.2022"))
    galleryList.add(Gallery(R.drawable.image02, "Петр I", "Marina", "08.2022"))
    galleryList.add(Gallery(R.drawable.image03, "Петр I", "Konstantn", "08.2022"))
    galleryList.add(Gallery(R.drawable.image04, "Собор", "Marina", "08.2022"))
    galleryList.add(Gallery(R.drawable.image05, "Император", "Konstantn", "08.2022"))
    galleryList.add(Gallery(R.drawable.image06, "Император", "Marina", "08.2022"))
    galleryList.add(Gallery(R.drawable.image07, "Рыбалка", "Konstantn", "09.2022"))
    galleryList.add(Gallery(R.drawable.image08, "Рыбалка", "Marina", "09.2022"))
    galleryList.add(Gallery(R.drawable.image09, "Рыбалка", "Konstantn", "09.2022"))
    galleryList.add(Gallery(R.drawable.image10, "Красная поляна", "Marina", "11.2022"))
    galleryList.add(Gallery(R.drawable.image11, "Красная поляна", "Konstantn", "11.2022"))


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            //.border(2.dp, Color.Magenta)
            .padding(10.dp)
    ) {

        ImageBlock(galleryList, currentIndex)

        Spacer(modifier = Modifier.height(32.dp))

        DescriptionTextBlock(galleryList, currentIndex)

        Spacer(modifier = Modifier.height(10.dp))

        ButtonBlock(
            onClickPrev = {
                if(currentIndex > 0)
                    currentIndex--
                          },
            onClickNext = {
                if(currentIndex < galleryList.size-1)
                    currentIndex++
            })
    }
    
}

@Composable
fun ImageBlock(
    galleryList: ArrayList<Gallery>,
    currentIndex: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        //.border(1.dp, Color.Red)
        .shadow(3.dp)
    ){
        Image(
            painter = painterResource(galleryList[currentIndex].image),
            contentDescription = "",
            modifier = Modifier
                .padding(15.dp)
        )
    }
}

@Composable
fun DescriptionTextBlock(
    galleryList: ArrayList<Gallery>,
    currentIndex: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            //.border(2.dp, Color.Red)
            .padding(10.dp)
    ) {
        Text(
            text = galleryList[currentIndex].title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Light,
            modifier = modifier
                .fillMaxWidth()
                //.border(2.dp, Color.Blue)
                .padding(10.dp, 10.dp, 10.dp, 0.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = galleryList[currentIndex].artist,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    //.border(2.dp, Color.Blue)
                    .padding(10.dp, 10.dp, 0.dp, 10.dp)
            )
            Text(
                text = "(${galleryList[currentIndex].year})",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = modifier
                    .fillMaxWidth()
                    //.border(2.dp, Color.Blue)
                    .padding(2.dp, 10.dp, 10.dp, 10.dp)
            )

        }
    }
}

@Composable
fun ButtonBlock(
    onClickPrev: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            //.border(2.dp, Color.Red)
            .padding(10.dp)

    ) {
        Button(
            onClick = {
                onClickPrev()
            },
            modifier = Modifier.size(150.dp, 40.dp)) {
            Text(text = "Previous")
        }
        Button(
            onClick = {
                onClickNext()
            },
            modifier = Modifier.size(150.dp, 40.dp)) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidBasicsLearningArtSpaceTheme {
        ArtSpaceLayout()
    }
}
