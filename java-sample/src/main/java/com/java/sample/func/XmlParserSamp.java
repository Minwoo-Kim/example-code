/**
 * 
 */
package com.java.sample.func;

import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.StringJoiner;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Minu.Kim
 *
 */
public class XmlParserSamp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new XmlParserSamp().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void start() throws Exception {
		String version = "1.0.0";
		String rootPath = "http://repo.hatiolab.com/nexus/content/repositories";
		String repositoryName = "jar_deployed";
		String groupId = "com.hatiolab";
		String artifactId = "elidom-base";
		String metaFileName = "maven-metadata.xml";

		// http://repo.hatiolab.com/nexus/content/repositories/jar_deployed/com/hatiolab/elidom-base/maven-metadata.xml
		StringJoiner path = new StringJoiner("/");
		path.add(rootPath).add(repositoryName).add(groupId.replace(".", "/")).add(artifactId).add(metaFileName);

		URL url = new URL(path.toString());
		URLConnection connection = url.openConnection();

		String userpass = "admin" + ":" + "elidom";
		String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));

		connection.setRequestProperty("Authorization", basicAuth);
		connection.setRequestProperty("X-Requested-With", "Curl");

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(connection.getInputStream());
		NodeList descNodes = doc.getElementsByTagName("versions");

		for (int i = 0; i < descNodes.getLength(); i++) {
			// 첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
			for (Node node = descNodes.item(i).getFirstChild(); node != null; node = node.getNextSibling()) {
				if (node.getNodeName().equals("version")) {
					String currentVersion = node.getTextContent();
					if (currentVersion.split("\\.").length == 3) {
						version = currentVersion;
					}
					break;
				}
			}
		}

		String[] versionInfo = version.split("\\.");
		int majorVersion = Integer.parseInt(versionInfo[0]);
		int minorVersion = Integer.parseInt(versionInfo[1]);
		// int pointVersion = Integer.parseInt(versionInfo[2]);
		int buildVersion = Integer.parseInt(versionInfo[2]);

		version = majorVersion + "." + minorVersion + "." + ++buildVersion;

		System.out.println(version);
	}
}
