# JenkinsTests-Unity

## Proje Açıklaması
Bu proje, Unity oyun geliştirme süreçlerinde otomatik test ve CI/CD entegrasyonu örneği sunar. EditMode ve PlayMode testleriyle kod kalitesini güvence altına alır, Jenkins pipeline ile otomatik test ve build işlemlerini destekler.

---

## Klasör Yapısı

```
Assets/
  Rainbow Jump/
    Scripts/           # Oyun ve yönetim scriptleri
    Prefabs/           # Hazır oyun nesneleri
    Sprites/           # Görseller
    Materials/         # Materyaller
    Audio/             # Ses dosyaları
    Fonts/             # Yazı tipleri
    Animations/        # Animasyonlar
    Scenes/            # Oyun sahneleri
  Tests/
    Editor/            # EditMode test scriptleri (otomasyon ve birim testler)

Builds/                # Derlenmiş oyun çıktıları
ProjectSettings/        # Unity proje ayarları
Packages/               # Bağımlılıklar ve paketler
Jenkinsfile.groovy      # Jenkins pipeline tanımı
```

---

## Test Altyapısı

### Test Framework Kurulumu
- Unity Editor’da **Window > Package Manager** üzerinden “Unity Test Framework” paketinin yüklü olduğundan emin olun.

### Test Klasörleri
- **EditMode testleri:** `Assets/Tests/Editor/` klasöründe tutulur.
- Her script için ayrı bir test dosyası oluşturulması önerilir.

### Örnek EditMode Test Scripti
`Assets/Tests/Editor/ManagerTests.cs`
```csharp
using NUnit.Framework;

public class ManagerTests
{
    [Test]
    public void Score_InitialValue_IsZero()
    {
        var manager = new Manager();
        Assert.AreEqual(0, manager.Score);
    }
}
```

### Testleri Çalıştırma
- Unity Editor’da **Window > General > Test Runner** ile testleri manuel çalıştırabilirsiniz.
- Komut satırında çalıştırmak için:

```sh
"C:/Program Files/Unity/Hub/Editor/2022.3.62f1/Editor/Unity.exe" \
  -batchmode \
  -projectPath "A:/Unity6/JenkinsTests-Unity/JenkistTests-Unity" \
  -runTests \
  -testPlatform editmode \
  -testResults "A:/Unity6/test-results-editmode.xml" \
  -quit
```

---

## Jenkins ile CI/CD

### Jenkinsfile.groovy
Proje kökünde yer alan `Jenkinsfile.groovy` ile otomatik test ve build işlemleri yapılır. Temel adımlar:

- Ortam değişkenleri ve parametreler tanımlanır.
- EditMode testleri otomatik olarak çalıştırılır.
- Test sonuçları XML dosyasına yazılır.
- (İsteğe bağlı) Sonuçlar Jenkins arayüzünde raporlanabilir.

### Örnek Jenkinsfile Aşaması
```groovy
stage('Build & Test') {
    steps {
        bat """
            cd /d "${env.PROJECT_PATH}"
            "${env.UNITY_PATH}" -runTests -batchmode -projectPath . -testResults "C:\\temp\\results.xml" -testPlatform editmode -logFile -
        """
    }
}
```

### Test Sonuçlarını Jenkins’te Yayınlama (Opsiyonel)
```groovy
post {
    always {
        junit 'C:\\temp\\results.xml'
    }
}
```

---

## Katkı ve Geliştirme
- Her yeni script için uygun testler yazılması önerilir.
=======
- Her yeni script için uygun testler yazılması önerilir.                  
>>>>>>> 516c3bcc74873059ef1ddd6610e692a5c13805a9
- Kodunuzu testlerle güvence altına alın.
- Proje yapısını ve klasör düzenini koruyun.


