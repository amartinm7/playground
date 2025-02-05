# LAB 1

install aws

`aws configure` to setup the 'default' and other profiles

`aws --version`

`aws configure list-profiles`

![lab_01.jpg](_img%2Flab_01.jpg)

`aws sts get-caller-identity --profile developer`

![lab_02.jpg](_img%2Flab_02.jpg)

`aws s3 ls --profile developer`

![lab_03.jpg](_img%2Flab_03.jpg)

`set bucketToDelete=<BucketNameToDelete>`

`aws s3 rb s3://%bucketToDelete% --profile developer`

`aws s3 rb s3://%bucketToDelete% --profile developer --debug`

![lab_04.jpg](_img%2Flab_04.jpg)

`aws iam list-policies --output text --query "Policies[?PolicyName == `S3-Delete-Bucket-Policy`].Arn" --profile developer`

![lab_05.jpg](_img%2Flab_05.jpg)

`set policyArn=<ARN-Place-Holder>`

`aws iam get-policy-version --policy-arn %policyArn% --version-id v1 --profile developer`

![lab_07.jpg](_img%2Flab_07.jpg)

`aws iam attach-role-policy --policy-arn %policyArn% --role-name notes-application-role --profile developer`

`aws iam list-attached-role-policies --role-name notes-application-role --profile developer`

![lab_08.jpg](_img%2Flab_08.jpg)

`aws s3 rb s3://%bucketToDelete% --profile developer`

`aws s3 ls --profile developer`

![lab_09.jpg](_img%2Flab_09.jpg)
