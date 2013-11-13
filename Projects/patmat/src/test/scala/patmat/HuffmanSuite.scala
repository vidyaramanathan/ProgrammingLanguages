package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val t3 = Leaf('a', 2)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }
  
  test("weight of a small tree") {
    new TestTrees {
      assert(weight(t3) === 2)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }
  
  test("chars of a small tree") {
    new TestTrees {
      assert(chars(t3) === List('a'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }
  
  test("times(List('a', 'b', 'a', 'c')") {
    assert(times(List('a', 'b', 'a', 'c')) === List(('a', 2), ('b', 1), ('c', 1)))
  }
  
  test("times(List())") {
    assert( times(List() ) === List())
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }
  
  test("singleton true") {
    assert(singleton(makeOrderedLeafList(List(('t', 2)))) === true)
  }
  
  test("singleton false") {
    assert(singleton(makeOrderedLeafList(List(('t', 2), ('e', 1)))) === false)
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("until") {
    val leaflist = List(Leaf('e', 2), Leaf('t', 1), Leaf('x', 4))
    assert(until(singleton, combine)(leaflist) === List(Fork(Leaf('t',1),Leaf('e',2),List('t', 'e'),3), Leaf('x',4)))
  }
  
  test("decode of french code") {
    new TestTrees {
      println(decodedSecret)
    }
  }

   test("encode a very short text should be identity") {
    new TestTrees {
      println(encode(t1)("ab".toList))
    }
  }
  
  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
