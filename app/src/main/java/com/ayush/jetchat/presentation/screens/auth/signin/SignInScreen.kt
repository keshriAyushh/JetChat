package com.ayush.jetchat.presentation.screens.auth.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ayush.jetchat.R
import com.ayush.jetchat.commons.Response
import com.ayush.jetchat.commons.Screen
import com.ayush.jetchat.commons.ShowProgress
import com.ayush.jetchat.commons.showError
import com.ayush.jetchat.presentation.screens.auth.AuthViewModel
import com.ayush.jetchat.presentation.ui.theme.Navy
import com.ayush.jetchat.presentation.ui.theme.Pink

@Composable
fun SignInScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {

    val signInState = authViewModel.signInState.collectAsState()
    val ctx = LocalContext.current

    signInState.value.let {
        when(it) {
            is Response.Error -> {
                showError(msg = it.message, ctx = ctx)
            }
            Response.Loading -> {
                ShowProgress()
            }
            is Response.Success -> {
                if(it.data) {
                    navController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.MainScreen.route) {
                            inclusive = true
                        }
                    }
                } else {
                    val email = rememberSaveable {
                        mutableStateOf("")
                    }

                    val password = rememberSaveable {
                        mutableStateOf("")
                    }

                    val showPasswordToggled = rememberSaveable {
                        mutableStateOf(false)
                    }

                    val annotatedString = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White
                            )
                        ) {
                            append("Jet")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Pink
                            )
                        ) {
                            append("Chat")
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Navy)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = annotatedString,
                            fontFamily = FontFamily(Font(R.font.font1)),
                            fontSize = 30.sp,
                            modifier = Modifier.padding(top = 50.dp)
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        OutlinedTextField(
                            value = email.value,
                            onValueChange = {
                                email.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(2.dp)),
                            placeholder = {
                                Text(
                                    text = "Email",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.font2))
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(
                                        R.drawable.email
                                    ),
                                    contentDescription = "email_icon",
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                cursorColor = Color.White,
                                unfocusedTrailingIconColor = Color.White,
                                focusedTrailingIconColor = Pink,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Pink,
                                unfocusedIndicatorColor = Color.Gray,
                                unfocusedTextColor = Color.LightGray
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = password.value,
                            onValueChange = {
                                password.value = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(2.dp)),
                            placeholder = {
                                Text(
                                    text = "Password",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.font2))
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = if (!showPasswordToggled.value) painterResource(
                                        R.drawable.password_hidden
                                    ) else painterResource(
                                        id = R.drawable.password_visible
                                    ),
                                    contentDescription = "password_icon",
                                    modifier = Modifier.clickable {
                                        showPasswordToggled.value = !showPasswordToggled.value
                                    }
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                cursorColor = Color.White,
                                unfocusedTrailingIconColor = Color.White,
                                focusedTrailingIconColor = Pink,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Pink,
                                unfocusedIndicatorColor = Color.Gray,
                                unfocusedTextColor = Color.LightGray
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            visualTransformation = if (!showPasswordToggled.value) PasswordVisualTransformation() else VisualTransformation.None
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Pink,
                                contentColor = Navy
                            ),
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(horizontal = 10.dp),
                            contentPadding = PaddingValues(all = 10.dp)
                        ) {
                            Text(
                                text = "Sign in",
                                fontFamily = FontFamily(Font(R.font.font1)),
                                fontSize = 18.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        val annotatedString2 = buildAnnotatedString {

                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(R.font.font2)),
                                    fontSize = 16.sp
                                )
                            ) {
                                append("New User? ")
                            }
                            pushStringAnnotation(tag = "sign up", annotation = Screen.SignUpScreen.route)
                            withStyle(
                                style = SpanStyle(
                                    color = Pink,
                                    fontFamily = FontFamily(Font(R.font.font2)),
                                    fontSize = 16.sp
                                )
                            ) {
                                append("Sign up")
                            }
                            pop()
                        }

                        ClickableText(
                            text = annotatedString2,
                            onClick = {
                                annotatedString2
                                    .getStringAnnotations("sign up", it, it)
                                    .firstOrNull()?.let { stringAnnotation ->
                                        navController.navigate(stringAnnotation.item) {
                                            popUpTo(stringAnnotation.item) {
                                                inclusive = true
                                            }
                                        }
                                    }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInPreview() {
    SignInScreen(navController = rememberNavController())
}