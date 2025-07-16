using NUnit.Framework;
using UnityEngine.TestTools;
using System.Collections;

public class EditModeTest
{
    [Test]
    public void SimpleAdditionTest()
    {
        int a = 2;
        int b = 3;
        int expected = 5;
        Assert.AreEqual(expected, a + b);
    }

    [UnityTest]
    public IEnumerator CoroutineTestExample()
    {
        int counter = 0;
        yield return null; // 1 frame bekle
        counter++;
        Assert.AreEqual(1, counter);
    }
}
