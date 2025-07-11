using NUnit.Framework;
using UnityEngine;

namespace EditModeTests
{
    public class FirstTest
    {
        [Test]
        public void SimpleSuccessTest()
        {
            Debug.Log("? EditMode test çalýþtý!");
            Assert.IsTrue(true);
        }
    }
}
