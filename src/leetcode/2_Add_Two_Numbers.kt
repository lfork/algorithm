package leetcode

/**
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 * Created by 98620 on 2018/10/15.
 */


fun main(args: Array<String>) {
    val solution = Solution2()

    val list1 = ListNode(1)
//
//    list1.next = ListNode(4)
//    (list1.next)?.next = ListNode(3)


    val list2 = ListNode(9)

    list2.next = ListNode(9)
//    (list2.next)?.next = ListNode(4)

    var result = solution.addTwoNumbers(list1, list2)

    println("????")
    if (result != null) {
        print("${result.`val`}")
        result = result.next
    }

    while (result != null) {
        print("->${result.`val`}")
        result = result.next
    }
}


// Definition for singly-linked list.
class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null
}

class Solution2 {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        var tmpNode1 = l1
        var tmpNode2 = l2
        var head: ListNode? = null
        var tempNode: ListNode? = null

        var carry = 0

        while ((tmpNode1 != null || tmpNode2 != null) || carry>0 ) {
            var num1 = 0
            var num2 = 0

            if (tmpNode1 != null) {
                num1 = tmpNode1.`val`
                tmpNode1 = tmpNode1.next
            }

            if (tmpNode2 != null) {
                num2 = tmpNode2.`val`
                tmpNode2 = tmpNode2.next
            }

            val tmpResult = (num1 + num2 + carry) % 10

            carry = if (num1 + num2 + carry >= 10) {
                (num1 + num2 + carry) / 10
            } else {
                0
            }


            if (tempNode == null) {
                tempNode = ListNode(tmpResult)
                head = tempNode

            } else{
                tempNode.next = ListNode(tmpResult)
                tempNode = tempNode.next
            }
        }
        return head
    }
}