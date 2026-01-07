1. Genera il token
	1.	Vai su GitHub → Settings
	2.	Developer settings
	3.	Personal access tokens → Tokens (classic)
	4.	Generate new token
	5.	Spunta almeno:
	•	repo
	•	read:org (se serve)
	6.	Copia il token (lo vedrai solo una volta!)
	7. git push origin main
	8. salvare nel keychain git config --global credential.helper osxkeychain 
	git config --global credential.helper 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

ALTERNATIVA
configurare ssh ssh-keygen -t ed25519 -C "tuamail@example.com"
Poi aggiungi la chiave pubblica su GitHub → Settings → SSH and GPG keys
e usa la URL SSH del repository:
git remote set-url origin git@github.com:username/repo.git

# create new repo
echo "# BatuTechEducation" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/aikira00/BatuTechEducation.git
git push -u origin main

# push repo 
git remote add origin https://github.com/aikira00/BatuTechEducation.git
git branch -M main
git push -u origin main