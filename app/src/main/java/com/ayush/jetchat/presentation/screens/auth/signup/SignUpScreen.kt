package com.ayush.jetchat.presentation.screens.auth.signup

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
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
import com.ayush.jetchat.data.model.User
import com.ayush.jetchat.presentation.screens.auth.AuthViewModel
import com.ayush.jetchat.presentation.ui.theme.Navy
import com.ayush.jetchat.presentation.ui.theme.Pink

@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthViewModel = hiltViewModel()) {

    val signUpState = authViewModel.signUpState.collectAsState()

    val ctx = LocalContext.current

    signUpState.value.let {
        when(it) {
            is Response.Error -> {
                showError(msg = it.message, ctx = ctx)
            }
            Response.Loading -> {
                ShowProgress()
            }
            is Response.Success -> {
                if(!it.data) {

                    val name = rememberSaveable {
                        mutableStateOf("")
                    }

                    val email = rememberSaveable {
                        mutableStateOf("")
                    }

                    val password = rememberSaveable {
                        mutableStateOf("")
                    }

                    val confirmPassword = rememberSaveable {
                        mutableStateOf("")
                    }

                    val isError = rememberSaveable {
                        mutableStateOf(false)
                    }

                    val isErrorInLength = rememberSaveable {
                        mutableStateOf(false)
                    }

                    val passwordLength = rememberSaveable {
                        mutableStateOf(false)
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
                                    text = "Name",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.font2))
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.AccountBox,
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
                                unfocusedTextColor = Color.LightGray,
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            visualTransformation = if (!showPasswordToggled.value) PasswordVisualTransformation() else VisualTransformation.None,
                            supportingText = {
                                if (password.value.length < 6) {
                                    Text(
                                        text = "* Password must be at least 6 characters long",
                                        color = Color.Red,
                                        fontFamily = FontFamily(Font(R.font.font2))
                                    )
                                }
                            },
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = confirmPassword.value,
                            onValueChange = {
                                confirmPassword.value = it
                                isError.value = confirmPassword.value == password.value
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(2.dp)),
                            placeholder = {
                                Text(
                                    text = "Confirm Password",
                                    color = Color.Gray,
                                    fontSize = 14.sp
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
                                unfocusedTextColor = Color.LightGray,
                                errorContainerColor = Color.Transparent,
                                errorIndicatorColor = Color.Red,
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions {
                                isError.value = confirmPassword.value != password.value
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            isError = !isError.value,
                            supportingText = {
                                if (!isError.value) {
                                    Text(
                                        text = "* Passwords don't match",
                                        color = Color.Red,
                                        fontFamily = FontFamily(Font(R.font.font2))
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Button(
                            onClick = {
                                if(email.value.isNotEmpty() && email.value.isNotBlank()) {
                                    if(password.value.isNotBlank() && password.value.isNotEmpty()) {
                                        val user = User(
                                            email = email.value
                                        )
                                        authViewModel.signUp(user = user, password = password.value)
                                    } else {
                                        showError(ctx = ctx, "Password cannot blank!")
                                    }
                                } else {
                                    showError(ctx = ctx, "Please enter a valid email id!")
                                }
                            },
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
                                text = "Sign up",
                                fontFamily = FontFamily(Font(R.font.font1)),
                                fontSize = 18.sp
                            )
                        }

                        val annotatedString2 = buildAnnotatedString {

                            withStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontFamily = FontFamily(Font(R.font.font2)),
                                    fontSize = 16.sp
                                )
                            ) {
                                append("Already have an account? ")
                            }
                            pushStringAnnotation(tag = "sign ip", annotation = Screen.SignInScreen.route)
                            withStyle(
                                style = SpanStyle(
                                    color = Pink,
                                    fontFamily = FontFamily(Font(R.font.font2)),
                                    fontSize = 16.sp
                                )
                            ) {
                                append("Sign in")
                            }
                            pop()
                        }

                        Spacer(modifier = Modifier.height(30.dp))

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
                } else {
                    navController.navigate(Screen.DetailsScreen.route) {
                        popUpTo(Screen.DetailsScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpPrev() {
    SignUpScreen(navController = rememberNavController())
}