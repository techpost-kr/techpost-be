# RDS 접속을 위한 AWS 자격 증명 설정 가이드
- AWS RDS 를 사용중으로 접속을 위해 AWS 자격 증명 설정이 필요합니다.
- AWS CLI 설치 및 설정 방법을 안내합니다.
- AWS Secrets Manager에서 RDS 접속 정보를 안전하게 관리할 수 있습니다.
- 본 가이드는 MacOS 기준으로 작성되었습니다. Windows나 Linux 사용자는 해당 OS에 맞는 설치 방법을 참고하세요.
- AWS CLI 공식 문서: https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html
- AWS Secrets Manager 공식 문서: https://docs.aws.amazon.com/secretsmanager/latest/userguide/intro.html
- AWS IAM 공식 문서: https://docs.aws.amazon.com/iam/latest/UserGuide/introduction.html
- AWS RDS 공식 문서: https://docs.aws.amazon.com/rds/index.html
- AWS 리전 정보: https://aws.amazon.com/about-aws/global-infrastructure/regions_az/
- AWS CLI 명령어 레퍼런스: https://awscli.amazonaws.com/v2/documentation/api/latest/index.html


## 1. AWS 계정 및 IAM 사용자 생성 (관리자 문의)
- 관리자로부터 AWS 계정을 발급받은 후 IAM 사용자 생성 및 권한 부여 필요
  1. 사용자 계정 발급 및 권한 부여
  2. 사용자 IP 에 대해서 RDS 접속 허용 필요 (관리자 문의) 
     - EC2 > 보안 그룹 > 보안 그룹 ID (sg-084333c1e5b84de73) 인바운드 규칙 편집 > MySQL/Aurora > 소스: 사용자 IP

## 2. ACCESS KEY 발급받기
- AWS CLI 사용을 위해 ACCESS KEY 발급 필요합니다.

1. AWS 콘솔 접속
2. IAM 서비스 > 사용자 > 해당 사용자 클릭
3. 보안 자격 증명 탭에서 "액세스 키 만들기" 클릭
4. Command Line Interface(CLI)
5. 발급받은 Access Key ID와 Secret Access Key 복사 (이후 다시는 Secret Key 못 봄!)

## 3. AWS CLI 설치 (MacOS 기준)
```bash
brew install awscli
```

## 4. AWS CLI 설치확인
```bash
aws --version
```

## 5. AWS CLI 로그인
```bash
aws configure
```
- AWS Access: {YOUR_ACCESS_KEY_ID}
- AWS Secret Access Key: {YOUR_SECRET_ACCESS_KEY}
- Default region name: ap-northeast-2
- Default output format: json
 
- 설정 완료 후 `~/.aws/credentials` 및 `~/.aws/config` 파일이 생성됨
```bash
cat ~/.aws/credentials
```

```bash 
cat ~/.aws/config
```

## 6. Secrets Manager 에서 RDS 정보 조회 (DEV 기준)
```bash 
aws secretsmanager get-secret-value  --secret-id dev/techpost/mysql  --region ap-northeast-2
```
