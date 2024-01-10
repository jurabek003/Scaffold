@file:Suppress("UNUSED_EXPRESSION")

package uz.turgunboyevjurabek.scaffold

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.drawable.Icon
import android.icu.number.Scale
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import uz.turgunboyevjurabek.scaffold.madels.Data
import uz.turgunboyevjurabek.scaffold.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScaffold()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My App") },
                navigationIcon = {
                    IconButton(onClick = {
                        // Handle navigation icon click
                        // For example, pop back from the navigation stack
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    // Add your action items here
                    IconButton(onClick = {
                        // Handle the click of the first action item
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                    IconButton(onClick = {
                        // Handle the click of the second action item
                    }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                    }
                },
//                modifier = Modifier
//                    .background(Color.Red)
//                    .fillMaxWidth(),
            )
        },
        content = {
            // Your main content goes here
            // For example, a Composable that represents the main screen of your app
            Column(
                modifier = Modifier
                    .padding(top = 75.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
            ) {
                val list=ArrayList<Data>()
                list.addAll(listOf(
                    Data(painterResource(id = R.drawable.r1),"Moshin","Cho'tki moshina vayy :)"),
                    Data(painterResource(id = R.drawable.r2),"Mushukcha","Cho'tki mushukcha vayy :)"),
                    Data(painterResource(id = R.drawable.r3),"Action","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.r4),"Math","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.r5),"Tabiat","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.r6),"Moshin","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.r7),"Moshin","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.r8),"Moshin","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.r9),"Moshin","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.r10),"Moshin","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.chart),"Moshin","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.moshin),"Moshin","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.ic_launcher_foreground),"Moshin","Cho'tki moshina vayy"),
                    Data(painterResource(id = R.drawable.ic_launcher_background),"Moshin","Cho'tki moshina vayy"),
                ))

                var showDialog by remember { mutableStateOf(false) }

                LazyColumn(){
                    items(list.size){
                        ItemUI(data = list[it], onItemClick = { showDialog=true })

                        if (showDialog){ CustomAlertDialog(onDismissRequest = { showDialog=false }, data = list[it]) }
                    }
                }
            }
        },
        floatingActionButton = {
            // Add your floating action button here
            FloatingActionButton(
                onClick = {
                    // Handle the click of the floating action button
                }
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }
        },
    )
}

@Preview(showSystemUi = true)
@Composable
fun MyScaffoldPreview() {
    ScaffoldTheme {
        MyScaffold()
    }
}


@Composable
fun ItemUI( data: Data,onItemClick: () -> Unit) {

    Card(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp))

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onItemClick }
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.DarkGray,
                            Color.Transparent,
                        ),
                    )
                ),
        ) {
            Spacer(modifier = Modifier
                .width(15.dp)
                .fillMaxHeight())
            Image(painter = data.img, contentDescription =null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),

            )

            Spacer(modifier = Modifier
                .width(15.dp)
                .fillMaxHeight())

            Column(modifier = Modifier) {
                Text(text = data.name, fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 18.sp)
                Text(text = data.description, fontWeight = FontWeight.ExtraBold, color = Color.White, fontSize = 14.sp)

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog( 
    onDismissRequest: () -> Unit,
    data: Data) {

    var isVisible by remember { mutableStateOf(true) }

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f, label = "",
        animationSpec = tween(durationMillis = 300
        ))
    val offsetY by animateDpAsState(
        targetValue = if (isVisible) 0.dp else 50.dp,
        animationSpec = tween(durationMillis = 300), label = ""
    )


    AlertDialog(onDismissRequest = {onDismissRequest},
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .offset(offsetY)
            .alpha(alpha)
            .background(MaterialTheme.colorScheme.background)
        ){
            Column(modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
            {
                Image(painter =data.img, contentDescription =null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape))
                Text(text =data.name, fontSize = 20.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraBold, color = Color.Red)
                Text(text =data.description, fontSize = 20.sp, fontFamily = FontFamily.Serif, fontWeight = FontWeight.ExtraBold, color = Color.Red)
            }
        }
    }



}
@Preview(showSystemUi = true)
@Composable
fun DialogPreview() {
    val data=Data(painterResource(id = R.drawable.r1),"Mushuk","Repper Mushukcha")

}