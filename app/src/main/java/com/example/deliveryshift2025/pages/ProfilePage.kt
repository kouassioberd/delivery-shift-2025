package com.example.deliveryshift2025.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deliveryshift2025.R

@Composable
fun ProfilePage( modifier: Modifier = Modifier) {
    var phoneNumber by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showCodeVerification by remember { mutableStateOf(false) }
    var verificationCode by remember { mutableStateOf("") }
    var codeErrorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!showCodeVerification) {
            PhoneNumberScreen(
                phoneNumber = phoneNumber,
                errorMessage = errorMessage,
                onPhoneNumberChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        phoneNumber = newValue
                        errorMessage = null
                    } else {
                        errorMessage = "Не можем ввести"
                    }
                },
                onLoginClick = {
                    if (phoneNumber.isEmpty()) {
                        errorMessage = "Поле является обязательным"
                    } else if (phoneNumber != "79234232343") {
                        errorMessage = "Неверный номер телефона"
                    } else {
                        errorMessage = null
                        showCodeVerification = true
                    }
                }
            )
        } else {
            CodeVerificationScreen(
                verificationCode = verificationCode,
                codeErrorMessage = codeErrorMessage,
                onCodeChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        verificationCode = newValue
                        codeErrorMessage = null
                    } else {7
                        codeErrorMessage = "Ввести не можем."
                    }
                },
                onVerifyClick = {
                    if (verificationCode.isEmpty()) {
                        codeErrorMessage = "Поле обязательно для заполнения"
                    } else if (verificationCode.length != 6) {
                        codeErrorMessage = "Код должен содержать 6 цифр"
                    } else if (verificationCode != "123456") {
                        codeErrorMessage = "Неверный код"
                    } else {
                        codeErrorMessage = null
                    }
                },
                onResendCode = {
                    verificationCode = ""
                    codeErrorMessage = null
                }
            )
        }
    }
}

@Composable
fun PhoneNumberScreen(
    phoneNumber: String,
    errorMessage: String?,
    onPhoneNumberChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.welcome_icon), contentDescription = "welcome image")
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Вход", fontSize = 40.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Введите номер телефона для входа", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "в личный кабинет", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Телефон", fontSize = 15.sp)
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = { Text(text = "Введите телефон") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorMessage != null
        )

        errorMessage?.let {
            Text(text = it, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onLoginClick) {
            Text(text = "Войти")
        }
    }
}

@Composable
fun CodeVerificationScreen(
    verificationCode: String,
    codeErrorMessage: String?,
    onCodeChange: (String) -> Unit,
    onVerifyClick: () -> Unit,
    onResendCode: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Проверочный код", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Введите код, отправленный на ваш номер", fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = verificationCode,
            onValueChange = onCodeChange,
            label = { Text(text = "Введите код") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = codeErrorMessage != null
        )

        codeErrorMessage?.let {
            Text(text = it, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onVerifyClick) {
            Text(text = "Проверить")
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Clickable text instead of TextButton
        Text(
            text = "Отправить ещё раз",
            fontSize = 16.sp,
            color = Color.Blue,
            modifier = Modifier
                .clickable { onResendCode() }
                .padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}

