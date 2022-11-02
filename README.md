# ChatFilter
Chat filter for your Nukkit server!

Config:
```
# Use '§' or '&' to change the color of the message.

messages:
  # Chat filter bypass permission: chatfilter.bypass
  warning-message: '§c§lThere is a forbidden word in your message. §r§7You can get a mute for excessive matting!'
  enable-warning-message: false

  block-message: "§c§lYour message has been blocked. §r§7You can get a mute for excessive matting!"
  enable-block-message: true

chatfilter:
  # Example: Hello will be replaced with ****
  replace-symbol: '*'
  # Available: block - message blocking, replace - replacing the message with stars.
  filter-type: "replace"

# You can continue this list as long as you want just copy my examples above
muted-words:
- 'word 1'
- 'word 2'
- 'word 3'

```


```
Without filter:
```
![no filter](https://user-images.githubusercontent.com/83061703/199034153-65ce8e26-ba24-4a43-8124-0a37d4a8f968.png)

```
With filter:
```
![filter](https://user-images.githubusercontent.com/83061703/199034166-44bd8bcd-105e-4ebc-8bd2-f3917968bfce.png)
