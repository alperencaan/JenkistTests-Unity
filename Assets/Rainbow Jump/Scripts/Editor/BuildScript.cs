using UnityEditor;
using System.IO;
using System.Linq;
using System.IO.Compression;

public class BuildScript
{
    public static void BuildWindows()
    {
        string path = "Builds/Windows";
        CreateDirectory(path);

        BuildPlayerOptions buildPlayerOptions = new BuildPlayerOptions
        {
            scenes = GetEnabledScenes(),
            locationPathName = $"{path}/MyGame.exe",
            target = BuildTarget.StandaloneWindows,
            options = BuildOptions.None
        };

        BuildPipeline.BuildPlayer(buildPlayerOptions); // Burada olmalý
        ZipBuild(path); // Burada olmalý
    }

    public static void CreateDirectory(string path)
    {
        if (!Directory.Exists(path))
        {
            Directory.CreateDirectory(path);
        }
    }

    private static string[] GetEnabledScenes()
    {
        return EditorBuildSettings.scenes
            .Where(scene => scene.enabled)
            .Select(scene => scene.path)
            .ToArray();
    }

    private static void ZipBuild(string path)
    {
        string zipPath = $"{path}/MyGame.zip";
        if (File.Exists(zipPath))
        {
            File.Delete(zipPath);
        }
        ZipFile.CreateFromDirectory(path, zipPath);
    }
}
