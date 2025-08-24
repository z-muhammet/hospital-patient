
# Hospital Patient Management API

Java + Spring Boot kullanılarak geliştirilmiş, CQRS ve PipelinR tabanlı bir hasta yönetim sistemidir. Veriler `.txt` dosyaları aracılığıyla saklanır, uygulama açılışında tüm dosyalar belleğe alınarak Singleton aracılığıyla tek servisten yönetilir.

##  Başlangıç


### Kurulum

```bash
git clone https://github.com/your-username/hospital-patient.git
cd hospital-patient
mvn clean install
```

### Uygulamayı Başlatma

```bash
mvn spring-boot:run
```

Varsayılan olarak 8081 portu üzerinden adresinde başlatılır.
Swagger page: 

> http://localhost:8081/swagger-ui/index.html

##  Mimarî

Uygulama `SOLID` prensiplerine uygun olarak katmanlı yapı ile geliştirilmiştir:

- `Controller`: REST endpoint'leri içerir.
- `Application`: Komutlar, sorgular ve iş mantıkları.
- `Model`: Entity ve veritabanı modeli.
- `Service`: Dosya yönetimi, veri işlemleri.
- `FileProcess`: Dosya okuma/yazma mantığı.
- `CQRS + PipelinR`: Komut ve sorgular için ayrık handler mantığı.

##  Teknolojiler

- **Spring Boot**
- **PipelinR** – CQRS iş mantığı için
- **Swagger** – API dokümantasyonu
- **Java IO** – Dosya tabanlı veri işlemleri
- **Singleton Pattern** – Bellekte veri yönetimi

##  Veri Yapısı

Her hasta bilgisi `key.txt` olarak saklanır:

```
name
surname
age
phone
email
```

Dosyalar `src/main/java/com/Hm/hospital_patient/DataBase/` içinde bulunur.

##  API Kullanımı

### Swagger UI

Uygulama çalıştığında [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html) adresinden Swagger arayüzüne erişebilirsiniz.

---

### GET `/api/V1/patients`

Tüm hastaları getirir.

**Response:**

```json
{
  "UUID123": {
    "name": "John",
    "surname": "Doe",
    "age": 35,
    "phone": "5551234567",
    "email": "john@example.com"
  }
    "UUID1234": {
    "name": "John2",
    "surname": "Doe2",
    "age": 35,
    "phone": "5551234567",
    "email": "john@example.com"
  }
}
```

---

### GET `/api/V1/patients/{id}`

Verilen `id`'ye göre hasta getirir.

**Response:**

```json
{
  "name": "Jane",
  "surname": "Smith",
  "age": 28,
  "phone": "5559876543",
  "email": "jane@example.com"
}
```

---

### POST `/api/V1/patients`

Yeni hasta ekler.

**Request Body:**

```json
{
  "name": "Alice",
  "surname": "Johnson",
  "age": 42,
  "phone": "5556789123",
  "email": "alice@example.com"
}
```

**Response:** `"new-patient-id"`

---

### PUT `/api/V1/patients`

Var olan hastayı günceller.

**Request Body:**

```json
{
  "id": "abc123",
  "name": "Alice",
  "surname": "Updated",
  "age": 43,
  "phone": "5556789123",
  "email": "alice@newmail.com"
}
```

**Response:** `"updated"`

---

### DELETE `/api/V1/patients?id=abc123`

Belirtilen hastayı siler.

**Response:** `"Patient deleted successfully"`

---


##  Test

Manuel testler için Swagger UI veya Postman kullanabilirsiniz.

---

##  Geliştirici Notları

- Tüm hasta verileri açılışta `SearchFile` aracılığıyla taranır, `ReadFile` ile okunur, Singleton üzerinde `globalUserMap` içinde tutulur.
- `databaseService`, bu Singleton veriyi işler ve günceller.
- Dosya silme ve yeniden oluşturma işlemleri güncelleme sırasında yapılır.
- `PipelinR` sayesinde her iş akışı kendi `Handler` sınıfında izole edilmiştir.
