using UnityEngine;
using UnityEngine.TestTools;
using NUnit.Framework;
using System.Collections;

public class PlayTesting
{
    private GameObject testObject;
    private TestMonoBehaviour testScript;

    [UnitySetUp]  // Her test �ncesi �al���r
    public IEnumerator Setup()
    {
        testObject = new GameObject("TestObject");
        testScript = testObject.AddComponent<TestMonoBehaviour>();
        yield return null; // Bir frame bekle ki Start/Awake �al��s�n
    }

    [UnityTearDown] // Her test sonras� �al���r
    public IEnumerator Teardown()
    {
        Object.Destroy(testObject);
        yield return null;
    }

    [UnityTest]
    public IEnumerator TestMonoBehaviourRuns()
    {
        int initialCount = testScript.frameCount;
        yield return null; // Bir frame ilerle

        Assert.Greater(testScript.frameCount, initialCount);
    }
}

public class TestMonoBehaviour : MonoBehaviour
{
    public int frameCount = 0;

    void Update()
    {
        frameCount++;
        Debug.Log("Update �al��t�: " + frameCount);
    }
}
