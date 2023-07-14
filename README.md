# ChatServer
ChatServer - это серверная программа на языке Java, которая реализует функционал чата. Она позволяет клиентам подключаться к серверу и обмениваться сообщениями.

## Установка
Склонируйте репозиторий с помощью команды:
Копировать

<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.0/highlight.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.8/clipboard.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.0/styles/default.min.css">

<script>
  document.addEventListener("DOMContentLoaded", function() {
    hljs.initHighlightingOnLoad();
    new ClipboardJS('.btn');
  });
</script>

<pre><code class="java hljs" id="code-snippet"> git clone https://github.com/username/ChatServer.git
</code></pre>

<button class="btn" data-clipboard-target="#code-snippet">Копировать</button>

Откройте проект в вашей любимой интегрированной среде разработки (IDE).
Зависимости
Проект не имеет дополнительных зависимостей.

## Настройка
Настройки сервера чата хранятся в файле settings.txt. Файл содержит две строки:

- IP-адрес сервера
- Порт сервера
- Установите необходимые значения IP-адреса и порта в settings.txt перед запуском сервера.

## Запуск
- Запустите программу, запустив класс ChatServer.
- Программа будет ожидать подключения клиентов и выводить сообщения о новых подключениях.
- Подключенные клиенты смогут обмениваться сообщениями через сервер.
  
## Взаимодействие с сервером
- В терминале сервера вы можете отправлять сообщения, которые будут доставлены всем подключенным клиентам.
- Клиенты могут подключаться к серверу с помощью приложений-клиентов, которые отправляют текстовые сообщения на сервер.
- Сообщения от клиентов будут отображаться в терминале сервера и доставляться всем остальным клиентам.
## Закрытие сервера
Чтобы остановить работу сервера, вызовите метод stopServer() класса ChatServer. Сервер перестанет принимать новые подключения и закроет соединения с существующими клиентами.

## Логирование
Все сообщения, отправленные через сервер, записываются в файл file.log. Файл содержит информацию о времени отправки сообщения, имени пользователя и самом сообщении.


# ChatClient

## Клиент для чата
Простой клиент для чата, написанный на языке программирования Java. Клиент подключается к серверу и позволяет пользователю отправлять и получать сообщения в реальном времени.

##Требования
Для использования клиента необходимо иметь установленную Java Virtual Machine (JVM) на компьютере.

## Установка и запуск
- Скомпилируйте исходный код клиента при помощи команды javac ChatClient.java.
- Запустите скомпилированный файл с помощью команды java ChatClient.

## Настройка
Настройки клиента загружаются из файла settings.txt. Файл должен содержать две строки:

- IP адрес сервера (первая строка)
- Порт сервера (вторая строка)

Пример файла settings.txt:

<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.0/highlight.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.8/clipboard.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.0/styles/default.min.css">

<script>
  document.addEventListener("DOMContentLoaded", function() {
    hljs.initHighlightingOnLoad();
    new ClipboardJS('.btn');
  });
</script>

<pre><code class="java hljs" id="code-snippet"> 127.0.0.1
8080
</code></pre>

<button class="btn" data-clipboard-target="#code-snippet">Копировать</button>

## Использование
- После запуска клиента, введите свое имя в консоли.
- После успешного подключения к серверу, вы сможете отправлять и принимать сообщения.
- Для отправки сообщения введите текст сообщения и нажмите Enter.
- Для выхода из чата введите команду /exit.

## Хранение логов
- Клиент записывает полученные сообщения в файл file.log. 
- Каждое сообщение сохраняется в виде:


<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.0/highlight.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/2.0.8/clipboard.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.0/styles/default.min.css">

<script>
  document.addEventListener("DOMContentLoaded", function() {
    hljs.initHighlightingOnLoad();
    new ClipboardJS('.btn');
  });
</script>

<pre><code class="java hljs" id="code-snippet"> [yyyy-MM-dd HH:mm:ss] message
</code></pre>

<button class="btn" data-clipboard-target="#code-snippet">Копировать</button>

## Завершение работы
Клиент будет завершать работу и закрывать соединение с сервером в случае ввода команды /exit или при неудачном подключении к серверу.