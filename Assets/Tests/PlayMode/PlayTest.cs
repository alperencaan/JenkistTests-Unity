using NUnit.Framework;
using UnityEngine.TestTools;
using Logic;

namespace PlayModeTests
{
    public class PlayTest
    {
        [UnityTest]
        public System.Collections.IEnumerator AddTest()
        {
            var logic = new PlayTestLogic();
            Assert.AreEqual(5, logic.Add(2, 3));
            yield return null;
        }
    }
}
