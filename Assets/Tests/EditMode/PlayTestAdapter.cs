using NUnit.Framework;
using Logic;

namespace EditModeTests
{
    public class PlayTestAdapter
    {
        [Test]
        public void AddTest_AsAdapter()
        {
            var logic = new PlayTestLogic();
            Assert.AreEqual(5, logic.Add(2, 3));
        }
    }
}
