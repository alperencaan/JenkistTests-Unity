using NUnit.Framework;
using Logic.EditModeTest;

public class PlayTestAdapter
{
    private PlayTestLogic logic;

    [SetUp]
    public void Setup()
    {
        logic = new PlayTestLogic();
    }

    [Test]
    public void EditMode_Addition_Works()
    {
        int result = logic.Add(2, 3);
        Assert.AreEqual(5, result);
    }
}
