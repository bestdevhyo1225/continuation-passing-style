package com.example.continuationtask

enum class Status {
    READY, // 초기 상태
    MARK, // 사용자가 어떻게 하고 싶다고 표시한 상태
    CONFIRM // 처리기가 MARK 한 것을 수용했다고 판단한 상태
}
