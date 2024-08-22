#!/bin/bash

export KUBECONFIG=/etc/kubernetes/admin.conf

curl -O https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml

sudo mv recommended.yaml dashboard.yaml

kubectl apply -f dashboard.yaml

cat <<EOF | sudo tee dashboard-adminuser.yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: admin-user
  namespace: kubernetes-dashboard
EOF

# apply the ServiceAccount template
kubectl apply -f dashboard-adminuser.yaml

# create ClusterRoleBinding if not exists from the k8s installation
cat <<EOF | sudo tee ClusterRoleBinding.yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: admin-user
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
- kind: ServiceAccount
  name: admin-user
  namespace: kubernetes-dashboard
EOF

# apply the ServiceAccount template
kubectl apply -f ClusterRoleBinding.yaml

# create the token to login into the dashboard
kubectl -n kubernetes-dashboard create token admin-user

# create the token and store it into a secret
cat <<EOF | sudo tee admin-user-secret.yml
apiVersion: v1
kind: Secret
metadata:
  name: admin-user
  namespace: kubernetes-dashboard
  annotations:
    kubernetes.io/service-account.name: "admin-user"
type: kubernetes.io/service-account-token
EOF

# apply the secret template
kubectl apply -f admin-user-secret.yml

# create the token and store it into the secret
kubectl get secret admin-user -n kubernetes-dashboard -o jsonpath={".data.token"} | base64 -d
