package com.spanishinquisition.treecompany.models

/*
 *  @author David Matei
 */

enum class QuestionType(int: Int) {
    Open(0),
    Single(1),
    Multi(2),
    Drop(3),
    Mail(4)
}