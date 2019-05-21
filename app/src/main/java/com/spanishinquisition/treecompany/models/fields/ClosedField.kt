package com.spanishinquisition.treecompany.models

data class ClosedField(
    var multipleChoice: Boolean,
    var options: List<String>
) : Field()