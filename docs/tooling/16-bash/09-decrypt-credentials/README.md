# decrypt credentials

```bash
aws kms decrypt --ciphertext-blob fileb://<(echo "ENCRYPTED_TEXT" | base64 -d) --output text --query Plaintext --region eu-west-1 --profile AWS_PROFILE | base64 -d
```
